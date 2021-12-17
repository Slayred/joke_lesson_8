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

class BaseRepository<E>(
    private val cacheDataSource: CacheDataSource<E>,
    private val cloudDataSource: CloudDataSource<E>,
    private val cached: CachedData<E>
): CommonRepository<E> {

    private var currentDataSource: DataFetcher<E> = cloudDataSource

    override suspend fun getCommonItem(): CommonDataModel<E> = withContext(Dispatchers.IO) {
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

    override suspend fun changeStatus(): CommonDataModel<E> {
        return cached.change(cacheDataSource)
    }




}