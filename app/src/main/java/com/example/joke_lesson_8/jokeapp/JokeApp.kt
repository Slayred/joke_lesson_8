package com.example.joke_lesson_8.jokeapp

import android.app.Application
import com.example.joke_lesson_8.data.*
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import io.realm.Realm
import com.example.joke_lesson_8.data.JokeSuccessMapper.*
import com.example.joke_lesson_8.presentation.QuoteViewModel

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: QuoteViewModel
    //private val BASE_URL = "http://92.63.192.103:3005"
    private val BASE_URL = "https://karljoke.herokuapp.com"
    //val cachedJoke = BaseCachedJoke()
    val cachedDataSource = BaseCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper())
    val resourceManager = BaseResourceManager(this)
    //val cloudDataSource = BaseCloudDataSourceOld(RetrofitFactory.getService(BASE_URL))
    val cloudDataSource = NewJokeCloudDataSource(RetrofitFactory.getService(BASE_URL))
    val repository = BaseJokeRepository(cachedDataSource, cloudDataSource,BaseCachedJoke())
    val interactor = BaseJokeInteractor(repository, JokeFailureHandlerFactory(resourceManager), JokeSuccessMapper())
//endregion


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        baseViewModel = BaseViewModel(interactor,BaseCommunication())
        quoteViewModel = QuoteViewModel(BaseCommunication())

    }
}
