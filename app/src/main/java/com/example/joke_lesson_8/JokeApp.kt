package com.example.joke_lesson_8

import android.app.Application
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.model.BaseModel
import com.example.joke_lesson_8.model.TestCloudDataSource
import com.example.joke_lesson_8.source.BaseCacheDataSource
import com.example.joke_lesson_8.source.BaseCloadDataSource
import com.example.joke_lesson_8.source.BaseRealmCachedDataSource
import com.example.joke_lesson_8.source.TestCacheDataSource
import io.realm.Realm

class JokeApp: Application() {

    lateinit var viewModel: ViewModel
    private val BASE_URL = "http://92.63.192.103:3005"

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        viewModel = ViewModel(BaseModel(BaseRealmCachedDataSource(Realm.getDefaultInstance())
            ,BaseCloadDataSource(RetrofitFactory.getService(BASE_URL))
            ,ResourceManager(this)))
    }
}
