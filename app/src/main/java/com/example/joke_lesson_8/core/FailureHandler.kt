package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.domain.*
import com.example.joke_lesson_8.interfaces.Failure
import com.example.joke_lesson_8.interfaces.ResourceManager
import com.example.joke_lesson_8.jokeapp.NoCached
import com.example.joke_lesson_8.jokeapp.NoConnection
import com.example.joke_lesson_8.jokeapp.SSLFailureException
import com.example.joke_lesson_8.jokeapp.ServiceUnavailible
import java.lang.Exception

interface FailureHandler {

    fun handle(e: Exception): Failure

}

class FailureHandlerFactory(private val resourceManager: ResourceManager): FailureHandler{

    override fun handle(e: Exception): Failure {
        return when(e){
            is NoCachedDataException -> NoCached(resourceManager)
            is NoConnectionException -> NoConnection(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailible(resourceManager)
            is SSLHandlerException -> SSLFailureException(resourceManager)
            else -> GenericError(resourceManager)
        }
    }

}