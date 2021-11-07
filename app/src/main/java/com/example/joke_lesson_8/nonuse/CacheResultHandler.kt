package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.domain.Joke

class CacheResultHandler(
    jokeDataFetcher: JokeDataFetcher,
    private val cachedJoke: CachedJoke,
    private val noCachedJoke: JokeFailure) :
    BaseResultHandlerOld<Joke, Unit>(jokeDataFetcher) {
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