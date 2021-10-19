package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.*
import com.example.joke_lesson_8.data.ErrorType
import com.google.gson.annotations.Until
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val baseResourceManager: BaseResourceManager
): Model {

    private val noConnection by lazy { NoConnection(baseResourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailible(baseResourceManager) }
    private val noCachedJoke by lazy { NoCachedJoke(baseResourceManager) }
    private val sslFailure by lazy { SSLFailure_exception(baseResourceManager) }
    private var jokeCallback: JokeCallback? = null

    private var cachedJoke: Joke? = null

    private var getJokeFromCache = false

    override suspend fun getJoke(): JokeUIModel = withContext(Dispatchers.IO) {
        var resultHandler = if (getJokeFromCache)
            CacheResultHandler(cacheDataSource)
        else CloudResultHandler(cloudDataSource)
        return@withContext resultHandler.process()
        //region before inner classes
//        if (getJokeFromCache) {
//            return@withContext when (val result = cacheDataSource.getJoke()) {
//                is Result.Success<Joke> -> result.data.let {
//                    cachedJoke = it
//                    it.toFavoriteJoke()
//                }
//                is Result.Error -> {
//                    cachedJoke = null
//                    FailedJokeUIModel(noCachedJoke.getMessage())
//                }
//            }
//
//        } else {
//            return@withContext when (val result = cloudDataSource.getJoke()) {
//                is Result.Success<JokeServerModel> -> {
//                    result.data.toJoke().let {
//                        cachedJoke = it
//                        it.toBaseJoke()
//                    }
//                }
//                is Result.Error<ErrorType> -> {
//                    cachedJoke = null
//                    var failure: JokeFailure? = when (result.exception) {
//                        ErrorType.NO_CONNECTION -> noConnection
//                        else -> serviceUnavailable
//                    }
//                    FailedJokeUIModel(failure!!.getMessage())
//                }
//            }
//        }
        //endregion
    }

    override fun initModel(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override suspend fun changeJokeStatus(): JokeUIModel? {

        return cachedJoke?.change(cacheDataSource)
    }


    override fun clear() {
        jokeCallback = null
    }

    override fun chooseDataSource(favorites: Boolean) {
        getJokeFromCache = favorites
    }

    private inner class CacheResultHandler(jokeDataFetcher: JokeDataFetcher<Joke, Unit>) :
        BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
        override fun handleResult(result: Result<Joke, Unit>): JokeUIModel {
            when (result) {
                is Result.Success<Joke> -> return result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    return FailedJokeUIModel(noCachedJoke.getMessage())
                }
            }
        }

    }

    private inner class CloudResultHandler(jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>) :
        BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {
        override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUIModel {
            when (result) {
                is Result.Success<JokeServerModel> -> return result.data.toJoke().let {
                    cachedJoke = it
                    it.toBaseJoke()
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    var failure: JokeFailure? = when (result.exception) {
                        ErrorType.NO_CONNECTION -> noConnection
                        ErrorType.SLL_ERROR ->  sslFailure
                        else -> serviceUnavailable
                    }
                   return FailedJokeUIModel(failure!!.getMessage())

                }
            }
        }

    }
}