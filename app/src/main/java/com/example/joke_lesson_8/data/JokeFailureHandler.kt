package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.domain.*
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.interfaces.ResourceManager
import com.example.joke_lesson_8.jokeapp.NoCachedJoke
import com.example.joke_lesson_8.jokeapp.NoConnection
import com.example.joke_lesson_8.jokeapp.SSLFailure_exception
import com.example.joke_lesson_8.jokeapp.ServiceUnavailible
import java.lang.Exception

interface JokeFailureHandler {

    fun handle(e: Exception): JokeFailure

}

class JokeFailureHandlerFactory(private val resourceManager: ResourceManager): JokeFailureHandler{

    override fun handle(e: Exception): JokeFailure {
        return when(e){
            is NoCachedJokesException -> NoCachedJoke(resourceManager)
            is NoConnectionException -> NoConnection(resourceManager)
            is ServiceUnavailableExcxeption -> ServiceUnavailible(resourceManager)
            is SSLHandlerException -> SSLFailure_exception(resourceManager)
            else -> GenericError(resourceManager)
        }
    }

}