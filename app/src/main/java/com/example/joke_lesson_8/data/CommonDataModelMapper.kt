package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.domain.CommonItem

interface CommonDataModelMapper<T> {
    fun map(id: Int, first: String, second: String, cached: Boolean): T
}

class CommonSuccessMapper: CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): CommonItem.Success {
        return CommonItem.Success(first, second, cached)
    }


class  JokeRealmMapper: CommonDataModelMapper<JokeRealmModel>{
        override fun map(
            id: Int,
            first: String,
            second: String,
            cached: Boolean
        ): JokeRealmModel {
           return JokeRealmModel().also { jokeRealmModel ->
               jokeRealmModel.id = id
               jokeRealmModel.text = first
               jokeRealmModel.punchline = second
           }
        }

    }


class QuoteRealmMapper: CommonDataModelMapper<QuoteRealmModel>{
    override fun map(id: Int, first: String, second: String, cached: Boolean): QuoteRealmModel {
        return QuoteRealmModel().also {
            quoteRealmModel ->
            quoteRealmModel.id = id
            quoteRealmModel.content = first
            quoteRealmModel.author = second
        }
    }

}

}