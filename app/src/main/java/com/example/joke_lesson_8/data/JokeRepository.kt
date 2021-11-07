package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface JokeRepository{

    suspend fun getJoke(): JokeDataModel

    suspend fun changeJokeStatus() : JokeDataModel

    fun chooseDataSource(favorites: Boolean)



}










