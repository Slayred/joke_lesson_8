package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.JokeUIModel

interface Model{

    suspend fun getJoke(): JokeUIModel

    fun initModel(callback: JokeCallback)

    suspend fun changeJokeStatus() : JokeUIModel?

    fun clear()

    fun chooseDataSource(favorites: Boolean)
}