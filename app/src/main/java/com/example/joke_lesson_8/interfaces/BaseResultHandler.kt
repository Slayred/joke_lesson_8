package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.model.JokeUIModel

abstract class BaseResultHandler<S,E> (private val jokeDataFetcher: JokeDataFetcher<S, E>):
    ResultHandler<S, E> {

    suspend fun process(): JokeUIModel {
        return handleResult(jokeDataFetcher.getJoke())
    }
}