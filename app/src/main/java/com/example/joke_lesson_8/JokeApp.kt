package com.example.joke_lesson_8

import android.app.Application
import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.model.BaseModel
import com.example.joke_lesson_8.model.TestCloudDataSource
import com.example.joke_lesson_8.source.TestCacheDataSource

class JokeApp: Application() {

    lateinit var viewModel: ViewModel
    private val BASE_URL = "http://92.63.192.103:3005"

    override fun onCreate() {
        super.onCreate()
//        val retr = Retrofit.Builder()
//            .baseUrl("https://habr.com/ru/post/314028/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        viewModel = ViewModel(
//            BaseModel(RetrofitFactory.getService("https://yesno.wtf"), ResourceManager(this)))
        viewModel = ViewModel(BaseModel(TestCacheDataSource(),TestCloudDataSource(), ResourceManager(this)))
    }
}
