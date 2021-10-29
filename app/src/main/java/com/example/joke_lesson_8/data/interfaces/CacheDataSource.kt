package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.interfaces.ChangeJokeStatus
import com.example.joke_lesson_8.jokeapp.Joke

interface CacheDataSource : JokeDataFetcher, ChangeJokeStatus {


}