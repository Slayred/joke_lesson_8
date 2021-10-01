package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.ErrorType
import com.example.joke_lesson_8.JokeService
import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.interfaces.JokeCloudCallback
import com.example.joke_lesson_8.model.JokeServerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class BaseCloadDataSource(private val service: JokeService): CloudDataSoruce {
    override fun getJoke(callback: JokeCloudCallback) {
        service.getJoke().enqueue(object : Callback<JokeServerModel>{
            override fun onResponse(
                call: Call<JokeServerModel>,
                response: Response<JokeServerModel>
            ) {
                if(response.isSuccessful){
                    callback.provide(response.body()!!)
                } else{
                    callback.fail(ErrorType.OTHER)
                }
            }

            override fun onFailure(call: Call<JokeServerModel>, t: Throwable) {
                if( t is UnknownHostException){
                    callback.fail(ErrorType.NO_CONNECTION)
                }  else if (t is SSLHandshakeException){
                    callback.fail(ErrorType.SLL_ERROR)
                } else callback.fail(ErrorType.OTHER)
            }

        })
    }
}