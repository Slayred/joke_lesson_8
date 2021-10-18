package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeServerModel

interface CacheDataSource : JokeDataFetcher<Joke,Unit>, ChangeJokeStatus {

    //suspend fun getJoke(): Result<Joke, Unit>

}