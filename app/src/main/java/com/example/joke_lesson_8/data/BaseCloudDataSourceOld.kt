package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.interfaces.BaseJokeService
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.JokeService
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.domain.NoConnectionException
import com.example.joke_lesson_8.domain.SSLHandlerException
import com.example.joke_lesson_8.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSourceOld(private val service: JokeService<*>): CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
        return try {
            Log.d("TAG", "BaseCloudDataSource getJoke()")
            var t = service.getJokeFromAPI().execute().body()!!.to()
            Log.d("TAG", t.toString())
            return t

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

abstract class BaseCloudDataSource<T: Mapper<JokeDataModel>>: CloudDataSource {

    protected abstract fun getJokeServerModel(): Call<T>

    override suspend fun getJoke(): JokeDataModel {
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