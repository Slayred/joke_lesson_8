package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.interfaces.Failure
import com.example.joke_lesson_8.jokeapp.BaseCommonUIModel
import com.example.joke_lesson_8.jokeapp.FailedCommonUIModel
import com.example.joke_lesson_8.jokeapp.FavoriteCommonUIModel
import com.example.joke_lesson_8.presentation.CommonUIModel

sealed class CommonItem<E> : Mapper<CommonUIModel<E>>{
    class Success<E>(
        private val id: E,
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ): CommonItem<E>(){
        override fun to(): CommonUIModel<E> {
            return  if( favorite) {
                FavoriteCommonUIModel(id, firstText,secondText)
            } else{
                BaseCommonUIModel(firstText, secondText)
            }
        }

    }

    class Failed(private val failure: Failure): CommonItem<Unit>(){
        override fun to(): CommonUIModel<Unit> {
            return FailedCommonUIModel(failure.getMessage())
        }

    }
}

