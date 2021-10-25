package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.interfaces.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudResultHandler: CloudResultHandler,
    private val cacheResultHandler: CacheResultHandler,
    private val cachedJoke: CachedJoke
): Model {

    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

    override suspend fun getJoke(): JokeUIModel = withContext(Dispatchers.IO) {

        return@withContext currentResultHandler.process()
    }
    override fun chooseDataSource(favorites: Boolean) {
        currentResultHandler = if (favorites) cacheResultHandler else cloudResultHandler
    }

    override suspend fun changeJokeStatus(): JokeUIModel? {

        return cachedJoke?.change(cacheDataSource)
    }




}