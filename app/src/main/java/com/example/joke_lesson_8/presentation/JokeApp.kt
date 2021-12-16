package com.example.joke_lesson_8.presentation

import android.app.Application
import com.example.joke_lesson_8.data.*
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import io.realm.Realm
import com.example.joke_lesson_8.data.CommonSuccessMapper.*
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.data.interfaces.QuoteService
import com.example.joke_lesson_8.domain.BaseIntercator

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel
    //private val BASE_OLD_JOKE_URL = "http://92.63.192.103:3005"
    private val BASE_Joke_URL = "https://karljoke.herokuapp.com"
    private val BASE_QUOTE_JOKE = "https://api.quotable.io/random/"
    val jokeCachedDataSource = JokeCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper(), JokeRealmToCommonMapper()
    )
    val quoteCachedDataSource = QuoteCachedDataSource(BaseRealmProvider(),QuoteRealmMapper(), QuoteRealmToCommonMapper())
    val resourceManager = BaseResourceManager(this)
    //val cloudDataSource = BaseCloudDataSourceOld(RetrofitFactory.getService(BASE_OLD_JOKE_URL))
    val newJokeCloudDataSource = NewJokeCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_Joke_URL)
        .create(NewJokeService::class.java))
    val quoteCloudDataSource = QuoteCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_QUOTE_JOKE)
        .create(QuoteService::class.java))
    val failureHandle = FailureHandlerFactory(resourceManager)
    val successMapper = CommonSuccessMapper()
    val jokeRepository = BaseRepository(jokeCachedDataSource, newJokeCloudDataSource,BaseCachedData())
    val quoteRepository = BaseRepository(quoteCachedDataSource,quoteCloudDataSource,BaseCachedData())

    val interactor = BaseIntercator(jokeRepository, failureHandle, successMapper)
//endregion


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        baseViewModel = BaseViewModel(interactor,BaseCommunication())
        //quoteViewModel = QuoteViewModel(BaseCommunication())
        quoteViewModel = BaseViewModel(BaseIntercator(quoteRepository, failureHandle, successMapper ), BaseCommunication())

    }
}
