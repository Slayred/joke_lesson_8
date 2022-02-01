package com.example.joke_lesson_8.presentation

import android.app.Application
import com.example.joke_lesson_8.data.*
import com.example.joke_lesson_8.factory.BaseRealmProvider
import com.example.joke_lesson_8.factory.RetrofitFactory
import com.example.joke_lesson_8.model.*
import io.realm.Realm
import com.example.joke_lesson_8.data.CommonSuccessMapper.*
import com.example.joke_lesson_8.data.cache.JokeCachedDataSource
import com.example.joke_lesson_8.data.cache.QuoteCachedDataSource
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.data.interfaces.QuoteService
import com.example.joke_lesson_8.data.net.NewJokeCloudDataSource
import com.example.joke_lesson_8.data.net.QuoteCloudDataSource
import com.example.joke_lesson_8.domain.BaseInteractor

class JokeApp: Application() {

//region existCode
    lateinit var baseViewModel: BaseViewModel<Int>
    lateinit var quoteViewModel: BaseViewModel<String>
    lateinit var jokeCommunication: BaseCommunication<Int>
    //private val BASE_OLD_JOKE_URL = "http://92.63.192.103:3005"
    private val BASE_Joke_URL = "https://karljoke.herokuapp.com"
    private val BASE_QUOTE_JOKE = "https://api.quotable.io/random/"
    private val jokeCachedDataSource = JokeCachedDataSource(BaseRealmProvider(),
        JokeRealmMapper(), JokeRealmToCommonMapper()
    )
    private val quoteCachedDataSource = QuoteCachedDataSource(BaseRealmProvider(),
        QuoteRealmMapper(), QuoteRealmToCommonMapper())
    private val resourceManager = BaseResourceManager(this)
    private val newJokeCloudDataSource = NewJokeCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_Joke_URL)
        .create(NewJokeService::class.java))
    private val quoteCloudDataSource = QuoteCloudDataSource(RetrofitFactory
        .getRetrofitInstance(BASE_QUOTE_JOKE)
        .create(QuoteService::class.java))
    private val failureHandle = FailureHandlerFactory(resourceManager)
    private val jokeSuccesMapper = CommonSuccessMapper<Int>()
    private val quoteSuccesMapper = CommonSuccessMapper<String>()
    private val jokeRepository = BaseRepository(jokeCachedDataSource, newJokeCloudDataSource,BaseCachedData<Int>())
    private val quoteRepository = BaseRepository(quoteCachedDataSource,quoteCloudDataSource,BaseCachedData<String>())

    private val jokeInteractor = BaseInteractor(jokeRepository, failureHandle, jokeSuccesMapper)
    private val quoteInteractor = BaseInteractor(quoteRepository, failureHandle, quoteSuccesMapper)
//endregion


    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        jokeCommunication = BaseCommunication()
        baseViewModel = BaseViewModel(jokeInteractor, jokeCommunication)
        quoteViewModel = BaseViewModel(quoteInteractor, BaseCommunication())

    }
}
