package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.JokeRepository
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.CachedJoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseJokeRepositoryInterface(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke)
: JokeRepository {

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

    override suspend fun changeJokeStatus(): JokeDataModel = cachedJoke.change(cacheDataSource)

    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if(favorites) cacheDataSource else cloudDataSource
    }
}