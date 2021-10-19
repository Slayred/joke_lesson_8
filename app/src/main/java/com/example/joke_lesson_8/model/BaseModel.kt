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
    private var cloudDataSource: CloudDataSource,
    private val baseResourceManager: BaseResourceManager
): Model {

    private val noConnection by lazy { NoConnection(baseResourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailible(baseResourceManager) }
    private val noCachedJoke by lazy { NoCachedJoke(baseResourceManager) }
    private val sslFailure by lazy { SSLFailure_exception(baseResourceManager) }
    private val cacheResultHandler by lazy { CacheResultHandler(cacheDataSource) }
    private val cloudResultHandler = CloudResultHandler(cloudDataSource)
    private var jokeCallback: JokeCallback? = null

    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

    private var cachedJoke: Joke? = null


    override suspend fun getJoke(): JokeUIModel = withContext(Dispatchers.IO) {

        return@withContext currentResultHandler.process()
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
        currentResultHandler = if (favorites) cacheResultHandler else cloudResultHandler
    }

    private inner class CacheResultHandler(jokeDataFetcher: JokeDataFetcher<Joke, Unit>) :
        BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
        override fun handleResult(result: Result<Joke, Unit>): JokeUIModel {
            return when (result) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    FailedJokeUIModel(noCachedJoke.getMessage())
                }
            }
        }

    }

    private inner class CloudResultHandler(jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
    private val cachedJoke: CachedJoke, private val noConnection: JokeFailure, private val serviceUnavailable: JokeFailure,
                                           private val sslFailureException: SSLFailure_exception
    ) :
        BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {
        override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUIModel {
            when (result) {
                is Result.Success<JokeServerModel> -> return result.data.toJoke().let {
                    cachedJoke.saveJoke(it)
                    it.toBaseJoke()
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke.clear()
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