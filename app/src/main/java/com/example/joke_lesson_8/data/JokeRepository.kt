package com.example.joke_lesson_8.data

interface JokeRepository{

    suspend fun getJoke(): JokeDataModel

    suspend fun changeJokeStatus() : JokeDataModel

    fun chooseDataSource(favorites: Boolean)



}










