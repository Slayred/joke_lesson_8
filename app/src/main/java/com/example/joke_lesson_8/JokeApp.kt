package com.example.joke_lesson_8

import android.app.Application
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JokeApp: Application() {

    lateinit var viewModel: ViewModel
    private final val BASE_URL = "http://92.63.192.103:3005"

    override fun onCreate() {
        super.onCreate()
//        val retr = Retrofit.Builder()
//            .baseUrl("https://habr.com/ru/post/314028/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        viewModel = ViewModel(
//            BaseModel(RetrofitFactory.getService("https://yesno.wtf"), ResourceManager(this)))
        viewModel = ViewModel(TestModel(ResourceManager(this)))
    }
}
