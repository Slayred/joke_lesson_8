package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.model.JokeUIModel

interface ChangeJoke {
    suspend fun change (changeJokeStatus: ChangeJokeStatus): JokeUIModel?
}