package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.JokeDataModel

interface JokeDataFetcher{
    suspend fun getJoke(): JokeDataModel
}