package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.interfaces.Failure
import com.example.joke_lesson_8.jokeapp.BaseCommonUIModel
import com.example.joke_lesson_8.jokeapp.FailedCommonUIModel
import com.example.joke_lesson_8.jokeapp.FavoriteCommonUIModel
import com.example.joke_lesson_8.presentation.CommonUIModel

sealed class CommonItem : Mapper<CommonUIModel>{
    class Success(
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ): CommonItem(){
        override fun to(): CommonUIModel {
            return  if( favorite) {
                FavoriteCommonUIModel(firstText,secondText)
            } else{
                BaseCommonUIModel(firstText, secondText)
            }
        }

    }

    class Failed(private val failure: Failure): CommonItem(){
        override fun to(): CommonUIModel {
            return FailedCommonUIModel(failure.getMessage())
        }

    }
}

