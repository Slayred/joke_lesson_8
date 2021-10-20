package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.FailedJokeUIModel
import com.example.joke_lesson_8.SSLFailure_exception
import com.example.joke_lesson_8.data.ErrorType
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.BaseResultHandler
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.JokeFailure

class CloudResultHandler(jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
                                 private val cachedJoke: CachedJoke, private val noConnection: JokeFailure,
                                 private val serviceUnavailable: JokeFailure,
                                 private val sslFailureException: SSLFailure_exception
) :
    BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {
    override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUIModel {
        when (result) {
            is Result.Success<JokeServerModel> -> return result.data.toJoke().let {
                cachedJoke.saveJoke(it)
                it.toBaseJoke()
            }
            is Result.Error<ErrorType> -> {
                cachedJoke.clear()
                var failure: JokeFailure? = when (result.exception) {
                    ErrorType.NO_CONNECTION -> noConnection
                    ErrorType.SLL_ERROR -> sslFailureException
                    else -> serviceUnavailable
                }
               return FailedJokeUIModel(failure!!.getMessage())

            }
        }
    }

}