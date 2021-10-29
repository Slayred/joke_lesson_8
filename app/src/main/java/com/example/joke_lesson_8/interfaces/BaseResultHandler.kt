package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.jokeapp.JokeUIModel

abstract class BaseResultHandler<S,E> (private val jokeDataFetcher: JokeDataFetcher<S, E>) {

    suspend fun process(): JokeUIModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: Result<S, E>) : JokeUIModel
}