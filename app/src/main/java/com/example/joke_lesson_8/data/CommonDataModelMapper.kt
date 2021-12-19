package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.data.cache.JokeRealmModel
import com.example.joke_lesson_8.data.cache.QuoteRealmModel
import com.example.joke_lesson_8.domain.CommonItem

interface CommonDataModelMapper<T, E> {
    fun map(id: E, first: String, second: String, cached: Boolean): T
}

class CommonSuccessMapper<E>: CommonDataModelMapper<CommonItem.Success, E> {
    override fun map(id: E, first: String, second: String, cached: Boolean): CommonItem.Success {
        Log.d("TAG", "Call CommonSuccesMapper")
        return CommonItem.Success(first, second, cached)
    }


class  JokeRealmMapper: CommonDataModelMapper<JokeRealmModel, Int>{
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


class QuoteRealmMapper: CommonDataModelMapper<QuoteRealmModel, String>{
    override fun map(id: String, first: String, second: String, cached: Boolean): QuoteRealmModel {
        return QuoteRealmModel().also {
            quoteRealmModel ->
            quoteRealmModel.id = id
            quoteRealmModel.content = first
            quoteRealmModel.author = second
        }
    }

}

}