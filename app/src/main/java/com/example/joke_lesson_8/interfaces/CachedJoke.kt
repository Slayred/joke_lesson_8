package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.JokeDataModel

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}