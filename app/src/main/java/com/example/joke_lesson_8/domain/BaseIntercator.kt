package com.example.joke_lesson_8.domain

import android.util.Log
import com.example.joke_lesson_8.data.CommonDataModelMapper
import com.example.joke_lesson_8.data.CommonRepository
import com.example.joke_lesson_8.data.FailureHandler
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import java.lang.Exception

class BaseIntercator (
    private val  repository: CommonRepository,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success>
): CommonIntercator {
    override suspend fun getItem(): CommonItem {
        return try {
                repository.getCommonItem().map(mapper)
        } catch (e: Exception){
            Log.d("TAG", "Fail in BaseIntercator \n" + e.message)
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): CommonItem {
        return try {
           repository.changeStatus().map(mapper)
        }catch (e: Exception){
            Log.d("TAG","BaseJokeInteractor changeFavourites() \n" +  e.message.toString())
            CommonItem.Failed(failureHandler.handle(e))
        }

    }

    override fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}