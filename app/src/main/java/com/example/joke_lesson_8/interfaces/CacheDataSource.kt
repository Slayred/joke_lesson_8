package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface CacheDataSource {

    fun addOrRemove(id: Int, joke: Joke): JokeUIModel

    //fun getJoke(jokeCachedCallback: JokeCachedCallback)
    fun getJoke(): Result<Joke, Unit>
}