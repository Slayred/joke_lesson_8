package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.jokeapp.CommonUIModel

interface ResultCallBack{

    fun provideJoke(commonUIModel: CommonUIModel)
}