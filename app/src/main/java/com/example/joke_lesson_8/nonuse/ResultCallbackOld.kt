package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.JokeFailure
import com.example.joke_lesson_8.JokeUIModel

interface ResultCallbackOld{

    fun provideSuccess(data: JokeUIModel)

    fun provideError(failure: JokeFailure)

}