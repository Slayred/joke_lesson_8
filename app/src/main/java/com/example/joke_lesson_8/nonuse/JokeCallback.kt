package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface JokeCallback {

    fun provide(jokeUIModel: JokeUIModel)
}