package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.interfaces.BaseJokeService
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.domain.NoConnectionException
import com.example.joke_lesson_8.domain.SSLHandlerException
import com.example.joke_lesson_8.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<CommonDataModel>>: CloudDataSource {

    protected abstract fun getJokeServerModel(): Call<T>

    override suspend fun getJoke(): CommonDataModel {
        try {
            return getJokeServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            Log.d("TAG", "getJoke from cloud Exception")
            Log.d("TAG", e.message.toString())
            when(e){
                is UnknownHostException -> throw NoConnectionException()
                is SSLHandlerException -> throw SSLHandlerException()
                else -> throw ServiceUnavailableException()

            }
        }
    }

}

class NewJokeCloudDataSource(private val service: NewJokeService):
    BaseCloudDataSource<NewJokeServerModel>(){

    override fun getJokeServerModel(): Call<NewJokeServerModel> {
        return service.getJokeFromAPI()
    }

}

class JokeCloudDataSource(private val service: BaseJokeService):
        BaseCloudDataSource<JokeServerModel>(){
    override fun getJokeServerModel(): Call<JokeServerModel> {
        return service.getJokeFromAPI()
    }

}