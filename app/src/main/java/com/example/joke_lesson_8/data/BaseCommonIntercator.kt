package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import com.example.joke_lesson_8.domain.*
import java.lang.Exception

class BaseCommonIntercator (
    private val  repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokerDataModelMapper<CommonItem.Success>
): CommonIntercator {
    override suspend fun getItem(): CommonItem {
        return try {
                repository.getJoke().map(mapper)
        } catch (e: Exception){
            CommonItem.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): CommonItem {
        return try {
           repository.changeJokeStatus().map(mapper)
        }catch (e: Exception){
            Log.d("TAG","BaseJokeInteractor changeFavourites() \n" +  e.message.toString())
            CommonItem.Failed(jokeFailureHandler.handle(e))
        }

    }

    override fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}