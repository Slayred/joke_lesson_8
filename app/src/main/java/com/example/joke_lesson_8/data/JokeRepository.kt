package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface JokeRepository{

    suspend fun getJoke(): JokeUIModel

    suspend fun changeJokeStatus() : JokeUIModel?

    fun chooseDataSource(favorites: Boolean)



}










