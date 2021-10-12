package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.interfaces.*

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSoruce: CloudDataSoruce,
    private val resourceManager: ResourceManager
): Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailible by  lazy { ServiceUnavailible(resourceManager) }
    private val noCachedJoke by  lazy { NoCachedJoke(resourceManager) }
    private var jokeCloudCallback: JokeCloudCallback? = null
    private var jokeCallback: JokeCallback? = null

    private var cachedJokeServerModel: JokeServerModel? = null
    private var cloudJokeServerModel: JokeServerModel? = null

    private var getJokeFromCache = false

    override fun getJoke() {
        if(getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback{
                override fun provide(jokeServerModel: JokeServerModel) {
                        cachedJokeServerModel = jokeServerModel
                        jokeCallback?.provide(jokeServerModel.toFavoriteJoke())
                }

                override fun fail() {
                    jokeCallback?.provide(FailedJoke(noCachedJoke.getMessage()))
                }

            })
        }
        else {
            cloudDataSoruce.getJoke(object : JokeCloudCallback{
                override fun provide(joke: JokeServerModel) {
                    cachedJokeServerModel = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJokeServerModel = null
                    val failure =
                        if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailible
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }


            })
        }
    }

    override fun initModel(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }


    override fun clear() {
        jokeCloudCallback = null
    }

    override fun chooseDataSource(favorites: Boolean) {
        getJokeFromCache = favorites
    }


}