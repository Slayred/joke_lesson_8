package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    //private val cloudResultHandler: CloudResultHandler,
    //private val cacheResultHandler: CacheResultHandler,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
): JokeRepository {

    //private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler
    private var currentDataSource: JokeDataFetcher = cloudDataSource

    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {
        try {
            val joke = currentDataSource.getJoke()
            cachedJoke.saveJoke(joke)
            return@withContext joke
        } catch (e: Exception){
            cachedJoke.clear()
            throw e
        }
    }
    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if (favorites) cacheDataSource else cloudDataSource
    }

    override suspend fun changeJokeStatus(): JokeDataModel {
        return cachedJoke.change(cacheDataSource)
    }




}