package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.model.Joke

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}