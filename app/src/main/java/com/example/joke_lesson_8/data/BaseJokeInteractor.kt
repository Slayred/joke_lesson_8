package com.example.joke_lesson_8.data

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
//    private val resourceManager: ResourceManager
    private val jokeFailureHandler: JokeFailureHandler
): JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            Joke.Success(repository.getJoke().text, repository.getJoke().punchlinle, false)
        } catch (e: Exception){
//            val message = when(e){
//                is NoCachedJokesException -> NoCachedJoke(resourceManager).getMessage()
//                is NoConnectionException -> NoConnection(resourceManager).getMessage()
//                is ServiceUnavailableExcxeption -> ServiceUnavailible(resourceManager).getMessage()
//                is SSLHandlerException -> SSLFailure_exception(resourceManager).getMessage()
//                else -> resourceManager.getString(R.string.generic_fail_messages)
//            }
//            Joke.Failed(message)
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): Joke {
        //TODO NEED TO FIX
        return Joke.Success(repository.getJoke().text, repository.getJoke().punchlinle, true)

    }

    override suspend fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}