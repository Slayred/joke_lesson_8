package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface ChangeJoke {
    suspend fun change (changeJokeStatus: ChangeJokeStatus): JokeDataModel?
}