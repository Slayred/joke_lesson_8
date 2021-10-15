package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.service.JokeService
import com.example.joke_lesson_8.interfaces.CloudDataSource
import com.example.joke_lesson_8.interfaces.JokeCloudCallback
import com.example.joke_lesson_8.model.JokeServerModel
import com.example.joke_lesson_8.service.ErrorType
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {
    override suspend fun getJoke() : Result<JokeServerModel, ErrorType> {
        return try {
            val result = service.getJoke()
            Result.Success(result)
                } catch (e: Exception) {
            val errorType =  if (e is UnknownHostException) {
                ErrorType.NO_CONNECTION
            } else if (e is SSLHandshakeException) {
                ErrorType.SLL_ERROR
            } else {
                ErrorType.OTHER
            }
            Result.Error(errorType)
        }
    }

//    override fun getJoke(callback: JokeCloudCallback) {
//        service.getJoke().enqueue(object : Callback<JokeServerModel>{
//            override fun onResponse(
//                call: Call<JokeServerModel>,
//                response: Response<JokeServerModel>
//            ) {
//                if(response.isSuccessful){
//                    callback.provide(response.body()!!.toJoke())
//                } else{
//                    callback.fail(ErrorType.OTHER)
//                }
//            }
//
//            override fun onFailure(call: Call<JokeServerModel>, t: Throwable) =
//                if( t is UnknownHostException){
//                    callback.fail(ErrorType.NO_CONNECTION)
//                }  else if (t is SSLHandshakeException){
//                    callback.fail(ErrorType.SLL_ERROR)
//                } else callback.fail(ErrorType.OTHER)
//
//        })
//
//    }
}