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
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.data.net.NewJokeCloudDataSource
import com.example.joke_lesson_8.data.net.QuoteCloudDataSource
import com.example.joke_lesson_8.domain.BaseInteractor
import com.example.joke_lesson_8.presentation.modules.JokesModule
import com.example.joke_lesson_8.presentation.modules.QuotesModule
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel
import com.example.joke_lesson_8.presentation.viewModels.ViewModelsFactory
import retrofit2.Retrofit

class JokeApp: Application() {



    //region existCode
//    lateinit var baseViewModel: BaseViewModel<Int>
//    lateinit var quoteViewModel: BaseViewModel<String>
//    lateinit var jokeCommunication: BaseCommunication<Int>
//    lateinit var quoteCommunication: BaseCommunication<String>
//    //private val BASE_OLD_JOKE_URL = "http://92.63.192.103:3005"
//    private val BASE_Joke_URL = "https://karljoke.herokuapp.com"
//    private val BASE_QUOTE_JOKE = "https://api.quotable.io/random/"
//    private val jokeCachedDataSource = JokeCachedDataSource(BaseRealmProvider(),
//        JokeRealmMapper(), JokeRealmToCommonMapper()
//    )
//    private val quoteCachedDataSource = QuoteCachedDataSource(BaseRealmProvider(),
//        QuoteRealmMapper(), QuoteRealmToCommonMapper())
//    private val resourceManager = BaseResourceManager(this)
//    private val newJokeCloudDataSource = NewJokeCloudDataSource(RetrofitFactory
//        .getRetrofitInstance(BASE_Joke_URL)
//        .create(NewJokeService::class.java))
//    private val quoteCloudDataSource = QuoteCloudDataSource(RetrofitFactory
//        .getRetrofitInstance(BASE_QUOTE_JOKE)
//        .create(QuoteService::class.java))
//    private val failureHandle = FailureHandlerFactory(resourceManager)
//    private val jokeSuccesMapper = CommonSuccessMapper<Int>()
//    private val quoteSuccesMapper = CommonSuccessMapper<String>()
//    private val jokeRepository = BaseRepository(jokeCachedDataSource, newJokeCloudDataSource,BaseCachedData<Int>())
//    private val quoteRepository = BaseRepository(quoteCachedDataSource,quoteCloudDataSource,BaseCachedData<String>())
//
//    private val jokeInteractor = BaseInteractor(jokeRepository, failureHandle, jokeSuccesMapper)
//    private val quoteInteractor = BaseInteractor(quoteRepository, failureHandle, quoteSuccesMapper)
//endregion


    val viewModelsFactory by lazy {
        ViewModelsFactory(
            JokesModule(failureHandler, realmProvider, retrofit),
            QuotesModule(failureHandler, realmProvider, retrofit)
        )
    }

    private lateinit var failureHandler: FailureHandler
    private lateinit var realmProvider: RealmProvider
    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        retrofit = RetrofitFactory.getRetrofitInstance("http://92.63.192.103:3005")
        failureHandler = FailureHandlerFactory(BaseResourceManager(this))
        realmProvider = BaseRealmProvider()
//        jokeCommunication = BaseCommunication()
//        quoteCommunication = BaseCommunication()
//        baseViewModel = BaseViewModel(jokeInteractor, jokeCommunication,"Jokes")
//        quoteViewModel = BaseViewModel(quoteInteractor, quoteCommunication ,"Quotes")


    }
}
