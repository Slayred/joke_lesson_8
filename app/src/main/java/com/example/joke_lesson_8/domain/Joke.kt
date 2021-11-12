package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.jokeapp.BaseCommonUIModel
import com.example.joke_lesson_8.jokeapp.FailedCommonUIModel
import com.example.joke_lesson_8.jokeapp.FavoriteCommonUIModel
import com.example.joke_lesson_8.jokeapp.CommonUIModel

sealed class Joke : Mapper<CommonUIModel>{
    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ): Joke(){
        override fun to(): CommonUIModel {
            return  if( favorite) {
                FavoriteCommonUIModel(text,punchline)
            } else{
                BaseCommonUIModel(text, punchline)
            }
        }

    }

    class Failed(private val failure: JokeFailure): Joke(){
        override fun to(): CommonUIModel {
            return FailedCommonUIModel(failure.getMessage())
        }

    }
}

