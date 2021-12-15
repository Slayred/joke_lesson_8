package com.example.joke_lesson_8.jokeapp

import android.app.Application
import com.example.joke_lesson_8.data.*
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import io.realm.Realm
import com.example.joke_lesson_8.data.CommonSuccessMapper.*
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.data.interfaces.QuoteService

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel
    //private val BASE_OLD_JOKE_URL = "http://92.63.192.103:3005"
    private val BASE_Joke_URL = "https://karljoke.herokuapp.com"
    private val BASE_QUOTE_JOKE = "https://api.quotable.io/random"
    val cachedDataSource = BaseCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper())
    val resourceManager = BaseResourceManager(this)
    //val cloudDataSource = BaseCloudDataSourceOld(RetrofitFactory.getService(BASE_OLD_JOKE_URL))
    val cloudDataSource = NewJokeCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_Joke_URL)
        .create(NewJokeService::class.java))
    val quoteCloudDataSource = QuoteCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_QUOTE_JOKE)
        .create(QuoteService::class.java))
    val failureHandle = FailureHandlerFactory(resourceManager)
    val successMapper = CommonSuccessMapper()
    val repository = BaseRepository(cachedDataSource, cloudDataSource,BaseCachedData())
    val interactor = BaseIntercator(repository, failureHandle, successMapper)
//endregion


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        baseViewModel = BaseViewModel(interactor,BaseCommunication())
        //quoteViewModel = QuoteViewModel(BaseCommunication())
        quoteViewModel = BaseViewModel(BaseIntercator(BaseRepository(object: CacheDataSource{
            override suspend fun getData(): CommonDataModel {
                TODO("Not yet implemented")
            }

            override suspend fun addOrRemove(id: Int, common: CommonDataModel): CommonDataModel {
                TODO("Not yet implemented")
            }

        }, quoteCloudDataSource , BaseCachedData()), failureHandle, successMapper ), BaseCommunication())

    }
}
