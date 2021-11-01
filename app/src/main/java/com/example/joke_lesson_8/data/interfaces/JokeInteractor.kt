package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.domain.Joke

interface JokeInteractor {

    suspend fun getJoke(): Joke

    suspend fun changeFavourites(): Joke

    suspend fun getFavoriteJokes(favorites: Boolean)
}