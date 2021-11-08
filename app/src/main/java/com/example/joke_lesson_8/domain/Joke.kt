package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.jokeapp.BaseJokeUiModel
import com.example.joke_lesson_8.jokeapp.FailedJokeUIModel
import com.example.joke_lesson_8.jokeapp.FavoriteJokeUIModel
import com.example.joke_lesson_8.jokeapp.JokeUIModel

sealed class Joke : Mapper<JokeUIModel>{
    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ): Joke(){
        override fun to(): JokeUIModel {
            return  if( favorite) {
                FavoriteJokeUIModel(text,punchline)
            } else{
                BaseJokeUiModel(text, punchline)
            }
        }

    }

    class Failed(private val failure: JokeFailure): Joke(){
        override fun to(): JokeUIModel {
            return FailedJokeUIModel(failure.getMessage())
        }

    }
}

