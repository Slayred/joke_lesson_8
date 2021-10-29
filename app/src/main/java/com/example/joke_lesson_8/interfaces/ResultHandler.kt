package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.jokeapp.JokeUIModel

interface ResultHandler<S,E>{
    fun handleResult(result: Result<S, E>) : JokeUIModel


}