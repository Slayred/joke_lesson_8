package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.Result

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}