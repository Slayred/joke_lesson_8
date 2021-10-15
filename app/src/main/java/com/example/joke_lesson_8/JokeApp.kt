package com.example.joke_lesson_8

import android.app.Application
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.BaseModel
import com.example.joke_lesson_8.source.BaseCloudDataSource
import com.example.joke_lesson_8.source.BaseRealmCachedDataSource
import io.realm.Realm

class JokeApp: Application() {

    lateinit var viewModelWork: ViewModelWork
    private val BASE_URL = "http://92.63.192.103:3005"

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        viewModelWork = ViewModelWork(BaseModel(BaseRealmCachedDataSource(Realm.getDefaultInstance())
            ,BaseCloudDataSource(RetrofitFactory.getService(BASE_URL))
            ,ResourceManager(this)))
    }
}
