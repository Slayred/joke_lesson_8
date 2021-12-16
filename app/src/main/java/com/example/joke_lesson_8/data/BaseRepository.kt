package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.DataFetcher
import com.example.joke_lesson_8.interfaces.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cached: CachedData
): CommonRepository {

    private var currentDataSource: DataFetcher = cloudDataSource

    override suspend fun getCommonItem(): CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cached.save(data)
            return@withContext data
        } catch (e: Exception){
            cached.clear()
            Log.d("TAG","Error from BaseRepository \n" + e.message.toString())
            throw e
        }
    }
    override fun chooseDataSource(favorites: Boolean) {
        currentDataSource = if (favorites) cacheDataSource else cloudDataSource
    }

    override suspend fun changeStatus(): CommonDataModel {
        return cached.change(cacheDataSource)
    }




}