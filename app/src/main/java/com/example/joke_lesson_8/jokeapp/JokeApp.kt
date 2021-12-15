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

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel
    //private val BASE_URL = "http://92.63.192.103:3005"
    private val BASE_URL = "https://karljoke.herokuapp.com"
    val cachedDataSource = BaseCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper())
    val resourceManager = BaseResourceManager(this)
    //val cloudDataSource = BaseCloudDataSourceOld(RetrofitFactory.getService(BASE_URL))
    val cloudDataSource = NewCloudDataSource(RetrofitFactory.getService(BASE_URL))
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

        }, object : CloudDataSource{
            override suspend fun getData(): CommonDataModel {
                TODO("Not yet implemented")
            }

        }, BaseCachedData()), failureHandle, successMapper ), BaseCommunication())

    }
}
