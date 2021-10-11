package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface CacheDataSource {

    fun addOrRemove(id: Int, joke: JokeServerModel): Joke

    fun getJoke(jokeCachedCallback: JokeCachedCallback)
}