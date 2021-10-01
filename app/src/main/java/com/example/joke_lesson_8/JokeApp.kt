package com.example.joke_lesson_8

import android.app.Application

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
        viewModel = ViewModel(TestModelOld(ResourceManager(this)))
    }
}
