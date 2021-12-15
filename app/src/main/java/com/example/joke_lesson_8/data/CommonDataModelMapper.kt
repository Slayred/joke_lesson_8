package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.domain.CommonItem

interface CommonDataModelMapper<T> {
    fun map(id: Int, text: String, punchline: String, cached: Boolean): T
}

class CommonSuccessMapper: CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): CommonItem.Success {
        return CommonItem.Success(text, punchline, cached)
    }


class  JokeRealmMapper: CommonDataModelMapper<JokeRealmModel>{
        override fun map(
            id: Int,
            text: String,
            punchline: String,
            cached: Boolean
        ): JokeRealmModel {
           return JokeRealmModel().also { jokeRealmModel ->
               jokeRealmModel.id = id
               jokeRealmModel.text = text
               jokeRealmModel.punchline = punchline
           }
        }

    }

}