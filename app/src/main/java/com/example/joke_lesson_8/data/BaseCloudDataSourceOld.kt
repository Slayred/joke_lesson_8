package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.interfaces.BaseJokeService
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.data.interfaces.QuoteService
import com.example.joke_lesson_8.domain.NoConnectionException
import com.example.joke_lesson_8.domain.SSLHandlerException
import com.example.joke_lesson_8.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<CommonDataModel<E>>, E>:
    CloudDataSource<E> {

    protected abstract fun getServerModel(): Call<T>

    override suspend fun getData(): CommonDataModel<E> {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            when(e){
                is UnknownHostException -> throw NoConnectionException()
                is SSLHandlerException -> throw SSLHandlerException()
                else -> throw ServiceUnavailableException()

            }
        }
    }

}

class NewJokeCloudDataSource(private val service: NewJokeService):
    BaseCloudDataSource<NewJokeServerModel, Int>(){

    override fun getServerModel(): Call<NewJokeServerModel> {
        return service.getJokeFromAPI()
    }

}

class QuoteCloudDataSource(private val service:QuoteService):
        BaseCloudDataSource<QuoteServerModel, String>(){
    override fun getServerModel(): Call<QuoteServerModel> {
        return service.getQuote()
    }

}

//class CloudDataSource(private val service: BaseJokeService):
//        BaseCloudDataSource<JokeServerModel>(){
//    override fun getServerModel(): Call<JokeServerModel> {
//        return service.getJokeFromAPI()
//    }
//
//}