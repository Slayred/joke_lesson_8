package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.jokeapp.FailedJokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.BaseResultHandler
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.domain.Joke
import com.example.joke_lesson_8.jokeapp.JokeUIModel

class CacheResultHandler(
    jokeDataFetcher: JokeDataFetcher,
    private val cachedJoke: CachedJoke,
    private val noCachedJoke: JokeFailure) :
    BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
    override fun handleResult(result: JokeDataModel): JokeDataModel {
        TODO("Not yet implemented")
    }
//    override fun handleResult(result: JokeDataModel): JokeUIModel {
//        return when (result) {
//            is Result.Success<Joke> -> result.data.let {
//                cachedJoke.saveJoke(it)
//                it.toFavoriteJoke()
//            }
//            is Result.Error -> {
//                cachedJoke.clear()
//                FailedJokeUIModel(noCachedJoke.getMessage())
//            }
//        }
//    }

}