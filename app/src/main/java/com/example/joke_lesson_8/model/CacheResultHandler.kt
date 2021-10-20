package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.FailedJokeUIModel
import com.example.joke_lesson_8.NoCachedJoke
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.BaseResultHandler
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.JokeFailure

class CacheResultHandler(
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val cachedJoke: CachedJoke,
    private val noCachedJoke: JokeFailure) :
    BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
    override fun handleResult(result: Result<Joke, Unit>): JokeUIModel {
        return when (result) {
            is Result.Success<Joke> -> result.data.let {
                cachedJoke.saveJoke(it)
                it.toFavoriteJoke()
            }
            is Result.Error -> {
                cachedJoke.clear()
                FailedJokeUIModel(noCachedJoke.getMessage())
            }
        }
    }

}