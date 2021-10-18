package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.*
import com.example.joke_lesson_8.data.ErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val baseResourceManager: BaseResourceManager
): Model {

    private val noConnection by lazy { NoConnection(baseResourceManager) }
    private val serviceUnavailable by  lazy { ServiceUnavailible(baseResourceManager) }
    private val noCachedJoke by  lazy { NoCachedJoke(baseResourceManager) }
    private var jokeCallback: JokeCallback? = null

    private var cachedJoke: Joke? = null

    private var getJokeFromCache = false

    override suspend fun getJoke(): JokeUIModel = withContext(Dispatchers.IO) {
        if(getJokeFromCache) {
           return@withContext when (val result = cacheDataSource.getJoke()){
                is Result.Success<Joke> -> result.data.let{
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    FailedJokeUIModel(noCachedJoke.getMessage())
                }
            }

        } else {
            return@withContext when (val result = cloudDataSource.getJoke()) {
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

        return cachedJoke?.change(cacheDataSource)
    }


    override fun clear() {
        jokeCallback = null
    }

    override fun chooseDataSource(favorites: Boolean) {
        getJokeFromCache = favorites
    }



}