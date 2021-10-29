package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface ResultCallBack{

    fun provideJoke(jokeUIModel: JokeUIModel)
}