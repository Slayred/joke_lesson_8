package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.jokeapp.JokeUIModel
import com.example.joke_lesson_8.jokeapp.Joke

interface ChangeJokeStatus {

    suspend fun addOrRemove(id: Int, joke: Joke) : JokeUIModel
}