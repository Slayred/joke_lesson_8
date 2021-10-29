package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.*
import com.example.joke_lesson_8.model.CacheResultHandler
import com.example.joke_lesson_8.model.CloudResultHandler
import com.example.joke_lesson_8.jokeapp.JokeUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudResultHandler: CloudResultHandler,
    private val cacheResultHandler: CacheResultHandler,
    private val cachedJoke: CachedJoke
): JokeRepository {

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