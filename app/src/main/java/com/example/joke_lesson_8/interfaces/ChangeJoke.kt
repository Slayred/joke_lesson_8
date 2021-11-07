package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.jokeapp.JokeUIModel
import java.lang.IllegalStateException

interface ChangeJoke {
    suspend fun change (changeJokeStatus: ChangeJokeStatus): JokeDataModel

    class Empty: ChangeJoke {
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
//            return JokeDataModel(0,"", "")
            throw IllegalStateException("empty change joke called")
        }

    }
}