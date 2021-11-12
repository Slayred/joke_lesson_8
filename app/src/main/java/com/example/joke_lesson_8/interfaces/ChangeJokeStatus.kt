package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.JokeDataModel

interface ChangeJokeStatus {

    suspend fun addOrRemove(id: Int, joke: JokeDataModel) : JokeDataModel
}