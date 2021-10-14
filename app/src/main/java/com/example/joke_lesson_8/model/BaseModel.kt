package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.interfaces.*

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSoruce: CloudDataSoruce,
    private val resourceManager: ResourceManager
): Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by  lazy { ServiceUnavailible(resourceManager) }
    private val noCachedJoke by  lazy { NoCachedJoke(resourceManager) }
    private var jokeCallback: JokeCallback? = null

    private var cachedJoke: Joke? = null

    private var getJokeFromCache = false

    override fun getJoke() {
        if(getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback{
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    jokeCallback?.provide((FailedJokeUIModel(noCachedJoke.getMessage())))
                }

            })
        }
        else {
//            cloudDataSoruce.getJoke(object : JokeCloudCallback{
//                override fun provide(joke: JokeServerModel) {
//                    cachedJoke = joke
//                    jokeCallback?.provide(joke.toBaseJoke())
//                }
//
//                override fun fail(error: ErrorType) {
//                    cachedJoke = null
//                    val failure =
//                        if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
//                    jokeCallback?.provide(FailedJokeUIModel(failure.getMessage()))
//                }
//
//
//            })
            cloudDataSoruce.getJoke(object : JokeCloudCallback{
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJoke = null
                    val  failure =
                        if(error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUIModel(failure.getMessage()))
                }
            })
        }
    }

    override fun initModel(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
//        cachedJoke?.change(cacheDataSource)?.let {
//            jokeCallback.provide(it)
//        }
        cachedJoke?.let {
            jokeCallback.provide(it.change(cacheDataSource))
        }
    }


    override fun clear() {
        jokeCallback = null
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }


}