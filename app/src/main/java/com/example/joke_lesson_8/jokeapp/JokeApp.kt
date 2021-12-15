package com.example.joke_lesson_8.jokeapp

import android.app.Application
import com.example.joke_lesson_8.data.*
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import io.realm.Realm
import com.example.joke_lesson_8.data.CommonSuccessMapper.*
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import com.example.joke_lesson_8.domain.CommonItem
import com.example.joke_lesson_8.presentation.QuoteViewModel

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: QuoteViewModel
    //private val BASE_URL = "http://92.63.192.103:3005"
    private val BASE_URL = "https://karljoke.herokuapp.com"
    val cachedDataSource = BaseCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper())
    val resourceManager = BaseResourceManager(this)
    //val cloudDataSource = BaseCloudDataSourceOld(RetrofitFactory.getService(BASE_URL))
    val cloudDataSource = NewJokeCloudDataSource(RetrofitFactory.getService(BASE_URL))
    val repository = BaseCommonRepository(cachedDataSource, cloudDataSource,BaseCachedCommonItem())
    val interactor = BaseIntercator(repository, FailureHandlerFactory(resourceManager), CommonSuccessMapper())
//endregion


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        baseViewModel = BaseViewModel(interactor,BaseCommunication())
        //quoteViewModel = QuoteViewModel(BaseCommunication())
        quoteViewModel = BaseViewModel(BaseIntercator(object: CommonRepository{
            override suspend fun getCommonItem(): CommonDataModel {
                TODO("Not yet implemented")
            }

            override suspend fun changeStatus(): CommonDataModel {
                TODO("Not yet implemented")
            }

            override fun chooseDataSource(favorites: Boolean) {
                TODO("Not yet implemented")
            }

        }, FailureHandlerFactory(resourceManager), CommonSuccessMapper() ), BaseCommunication())

    }
}
