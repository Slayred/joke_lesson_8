package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.model.Joke

interface ChangeJokeStatus {

    suspend fun addOrRemove(id: Int, joke: Joke) : JokeUIModel
}