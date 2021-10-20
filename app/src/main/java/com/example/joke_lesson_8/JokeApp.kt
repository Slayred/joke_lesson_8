package com.example.joke_lesson_8

import android.app.Application
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import com.example.joke_lesson_8.source.BaseCloudDataSource
import com.example.joke_lesson_8.source.BaseCachedDataSource
import io.realm.Realm

class JokeApp: Application() {

    lateinit var viewModelWork: ViewModelWork
    private val BASE_URL = "http://92.63.192.103:3005"
    val cachedJoke = BaseCachedJoke()
    val cachedDataSource = BaseCachedDataSource(BaseRealmProvider())
    val cloudDataSource = BaseCloudDataSource(RetrofitFactory.getService(BASE_URL))
    val resourceManager = BaseResourceManager(this)

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        viewModelWork = ViewModelWork(BaseModel(cachedDataSource,
        CloudResultHandler(cloudDataSource,cachedJoke,NoConnection(resourceManager),ServiceUnavailible(resourceManager),SSLFailure_exception(resourceManager)),
            CacheResultHandler(cachedDataSource,cachedJoke,NoCachedJoke(resourceManager)),
            cachedJoke))
    }
}
