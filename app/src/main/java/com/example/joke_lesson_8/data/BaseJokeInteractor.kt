package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.data.interfaces.JokeInteractor
import com.example.joke_lesson_8.domain.*
import com.example.joke_lesson_8.interfaces.ResourceManager
import com.example.joke_lesson_8.jokeapp.NoCachedJoke
import com.example.joke_lesson_8.jokeapp.NoConnection
import com.example.joke_lesson_8.jokeapp.SSLFailure_exception
import com.example.joke_lesson_8.jokeapp.ServiceUnavailible
import com.example.joke_lesson_8.model.BaseResourceManager
import java.lang.Exception

class BaseJokeInteractor (
    private val  repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokerDataModelMapper<Joke.Success>
): JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
                repository.getJoke().map(mapper)
        } catch (e: Exception){
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): Joke {
        //TODO NEED TO FIX╬
        return try {
            //TODO NEED TO FIX this call must change status
           repository.changeJokeStatus().map(mapper)
        }catch (e: Exception){
            Log.d("TAG","BaseJokeInteractor changeFavourites() \n" +  e.message.toString())
            Joke.Failed(jokeFailureHandler.handle(e))
        }

    }

    override suspend fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}