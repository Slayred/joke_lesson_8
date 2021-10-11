package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.interfaces.JokeCallback
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSoruce: CloudDataSoruce,
    private val resourceManager: ResourceManager
): Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailible by  lazy { ServiceUnavailible(resourceManager) }
    private var jokeCloudCallback: JokeCloudCallback? = null
    private var jokeCallback: JokeCallback? = null

    private var cachedJokeServerModel: JokeServerModel? = null

    private var getJokeFromCache = false

    override fun getJoke() {
        if(getJokeFromCache) {
            cloudDataSoruce.getJoke(object : JokeCloudCallback {
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
        else {
            cloudDataSoruce.getJoke(object : JokeCloudCallback{
                override fun provide(joke: JokeServerModel) {
                    TODO("Not yet implemented")
                }

                override fun fail(error: ErrorType) {
                    TODO("Not yet implemented")
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