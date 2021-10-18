package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.model.JokeUIModel

interface ResultCallBack{

    fun provideJoke(jokeUIModel: JokeUIModel)
}