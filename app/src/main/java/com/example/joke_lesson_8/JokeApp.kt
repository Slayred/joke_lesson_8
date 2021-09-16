package com.example.joke_lesson_8

import android.app.Application
import com.google.gson.Gson
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JokeApp: Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retr = Retrofit.Builder()
            .baseUrl("https://habr.com/ru/post/314028/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(
            BaseModel(retr.create(JokeService::class.java), ResourceManager(this)))
    }
}
