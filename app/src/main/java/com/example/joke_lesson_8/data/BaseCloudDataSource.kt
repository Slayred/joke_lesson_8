package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.JokeService
import com.example.joke_lesson_8.domain.NoConnectionException
import com.example.joke_lesson_8.domain.SSLHandlerException
import com.example.joke_lesson_8.domain.ServiceUnavailableException
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
        return try {
            Log.d("TAG", "BaseCloudDataSource getJoke()")
            //TODO Fix service
            var t = service.getJokeFromAPI().to()
            Log.d("TAG", t.toString())
            //return service.getJokeFromAPI().to()
            return t

        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            when(e){
                is UnknownHostException -> throw NoConnectionException()
                is SSLHandlerException -> throw SSLHandlerException()
                else -> throw ServiceUnavailableException()

            }
        }
    }


//    override suspend fun getJoke() : Result<JokeServerModel, ErrorType> {
//        return try {
//            val result = service.getJoke()
//                Result.Success(result)
//                } catch (e: Exception) {
//            val errorType =  if (e is UnknownHostException) {
//                ErrorType.NO_CONNECTION
//            } else if (e is SSLHandshakeException) {
//                ErrorType.SLL_ERROR
//            } else {
//                ErrorType.OTHER
//            }
//            Result.Error(errorType)
//        }
//    }

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