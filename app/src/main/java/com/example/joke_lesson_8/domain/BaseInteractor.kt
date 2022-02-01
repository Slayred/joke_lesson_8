package com.example.joke_lesson_8.domain

import android.util.Log
import com.example.joke_lesson_8.data.CommonDataModelMapper
import com.example.joke_lesson_8.data.interfaces.CommonRepository
import com.example.joke_lesson_8.data.FailureHandler
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import java.lang.Exception

class BaseInteractor<E> (
    private val  repository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success<E>, E>
): CommonIntercator<E> {
    override suspend fun getItem(): CommonItem<E> {
        return try {
                repository.getCommonItem().map(mapper)
        } catch (e: Exception){
            Log.d("TAG", "Fail in BaseIntercator \n" + e.message)
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun getItemList(): List<CommonItem<E>> {
        return try {
            repository.getCommonItemList().map {
                it.map(mapper)
            }
        }catch (e: Exception) {
            Log.d("TAG", "Fail in BaseIntercator \n" + e.message)
            listOf(CommonItem.Failed(failureHandler.handle(e)))
        }
    }

    override suspend fun changeFavourites(): CommonItem<E> {
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

    override suspend fun removeItem(id: E) {
        repository.remove(id)
    }
}