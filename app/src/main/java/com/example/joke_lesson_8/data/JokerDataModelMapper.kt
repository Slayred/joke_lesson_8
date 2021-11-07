package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.domain.Joke

interface JokerDataModelMapper<T> {
    fun map(id: Int, text: String, punchline: String, cached: Boolean): T
}

class JokeSuccessMapper: JokerDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): Joke.Success {
        return Joke.Success(text, punchline, cached)
    }


class  JokeRealmMapper: JokerDataModelMapper<JokeRealmModel>{
        override fun map(
            id: Int,
            text: String,
            punchline: String,
            cached: Boolean
        ): JokeRealmModel {
           return JokeRealmModel().also { jokeRealmModel ->
               jokeRealmModel.id
               jokeRealmModel.text
               jokeRealmModel.punchline
           }
        }

    }

}