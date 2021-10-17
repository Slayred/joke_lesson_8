package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.*
import com.example.joke_lesson_8.service.ErrorType
import java.lang.Exception

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
): Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by  lazy { ServiceUnavailible(resourceManager) }
    private val noCachedJoke by  lazy { NoCachedJoke(resourceManager) }
    private var jokeCallback: JokeCallback? = null

    private var cachedJoke: Joke? = null

    private var getJokeFromCache = false

//region beforeCoroutines
//    override fun getJoke() {
//        if(getJokeFromCache) {
//            cacheDataSource.getJoke(object : JokeCachedCallback{
//                override fun provide(joke: Joke) {
//                    cachedJoke = joke
//                    jokeCallback?.provide(joke.toFavoriteJoke())
//                }
//
//                override fun fail() {
//                    jokeCallback?.provide((FailedJokeUIModel(noCachedJoke.getMessage())))
//                }
//
//            })
//        }
//        else {
////            cloudDataSoruce.getJoke(object : JokeCloudCallback{
////                override fun provide(joke: JokeServerModel) {
////                    cachedJoke = joke
////                    jokeCallback?.provide(joke.toBaseJoke())
////                }
////
////                override fun fail(error: ErrorType) {
////                    cachedJoke = null
////                    val failure =
////                        if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
////                    jokeCallback?.provide(FailedJokeUIModel(failure.getMessage()))
////                }
////
////
////            })
//            cloudDataSource.getJoke(object : JokeCloudCallback{
//                override fun provide(joke: Joke) {
//                    cachedJoke = joke
//                    jokeCallback?.provide(joke.toBaseJoke())
//                }
//
//                override fun fail(error: ErrorType) {
//                    cachedJoke = null
//                    val  failure =
//                        if(error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
//                    jokeCallback?.provide(FailedJokeUIModel(failure.getMessage()))
//                }
//            })
//        }
//    }
//endregion

    override suspend fun getJoke(): JokeUIModel {
        if(getJokeFromCache) {
           return when (val result = cacheDataSource.getJoke()){
                is Result.Success<Joke> -> result.data.let{
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error<Unit> -> {
                    cachedJoke = null
                    FailedJokeUIModel(noCachedJoke.getMessage())
                }
            }

        } else {
            return when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    var failure: JokeFailure? = when (result.exception) {
                        ErrorType.NO_CONNECTION -> noConnection
                        else -> serviceUnavailable
                    }
                    FailedJokeUIModel(failure!!.getMessage())
                }
            }
        }
    }

    override fun initModel(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override suspend fun changeJokeStatus(): JokeUIModel? {
//        cachedJoke?.change(cacheDataSource)?.let {
//            jokeCallback.provide(it)
//        }
//        cachedJoke?.let {
//            jokeCallback.provide(it.change(cacheDataSource))
//        }
        return cachedJoke?.change(cacheDataSource)
    }


    override fun clear() {
        jokeCallback = null
    }

    override fun chooseDataSource(favorites: Boolean) {
        getJokeFromCache = favorites
    }


}