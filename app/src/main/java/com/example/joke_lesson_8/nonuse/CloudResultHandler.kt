package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.jokeapp.SSLFailure_exception
import com.example.joke_lesson_8.data.ErrorType
import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.JokeServerModel
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.data.interfaces.JokeDataFetcher
import com.example.joke_lesson_8.interfaces.JokeFailure

class CloudResultHandler(jokeDataFetcher: JokeDataFetcher,
                         private val cachedJoke: CachedJoke, private val noConnection: JokeFailure,
                         private val serviceUnavailable: JokeFailure,
                         private val sslFailureException: SSLFailure_exception
) :
    BaseResultHandlerOld<JokeServerModel, ErrorType>(jokeDataFetcher) {
    override fun handleResult(result: JokeDataModel): JokeDataModel {
        TODO("Not yet implemented")
    }

//    override fun handleResult(result: JokeDataModel): JokeUIModel {
//        when (result) {
//            is Result.Success<JokeServerModel> -> return result.data.to().let {
//                cachedJoke.saveJoke(it)
//                it.toBaseJoke()
//            }
//            is Result.Error<ErrorType> -> {
//                cachedJoke.clear()
//                var failure: JokeFailure? = when (result.exception) {
//                    ErrorType.NO_CONNECTION -> noConnection
//                    ErrorType.SLL_ERROR -> sslFailureException
//                    else -> serviceUnavailable
//                }
//               return FailedJokeUIModel(failure!!.getMessage())
//
//            }
//        }
//    }

}