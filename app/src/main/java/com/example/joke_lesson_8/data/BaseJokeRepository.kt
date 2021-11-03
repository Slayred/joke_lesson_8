package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.*
import com.example.joke_lesson_8.model.CacheResultHandler
import com.example.joke_lesson_8.model.CloudResultHandler
import com.example.joke_lesson_8.jokeapp.JokeUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    //private val cloudResultHandler: CloudResultHandler,
    //private val cacheResultHandler: CacheResultHandler,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
): JokeRepository {

    //private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler
    private var currentDataSource: JokeDataFetcher = cacheDataSource

    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {

        return@withContext currentDataSource.getJoke()
    }
    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if (favorites) cacheDataSource else cloudDataSource
    }

    override suspend fun changeJokeStatus(): JokeDataModel? {

        return cachedJoke.change(cacheDataSource)
    }




}