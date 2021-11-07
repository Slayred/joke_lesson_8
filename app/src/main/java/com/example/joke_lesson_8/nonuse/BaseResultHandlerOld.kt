package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher

abstract class BaseResultHandlerOld<S,E> (private val jokeDataFetcher: JokeDataFetcher) {

    suspend fun process(): JokeDataModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: JokeDataModel) : JokeDataModel
}