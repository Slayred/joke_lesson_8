package com.example.joke_lesson_8.presentation.modules

import com.example.joke_lesson_8.data.BaseRepository
import com.example.joke_lesson_8.data.CommonSuccessMapper
import com.example.joke_lesson_8.data.FailureHandler
import com.example.joke_lesson_8.data.QuoteRealmToCommonMapper
import com.example.joke_lesson_8.data.cache.QuoteCachedDataSource
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.interfaces.QuoteService
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.data.net.QuoteCloudDataSource
import com.example.joke_lesson_8.domain.BaseInteractor
import com.example.joke_lesson_8.model.BaseCachedData
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.QuoteViewModel
import retrofit2.Retrofit

class QuotesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
): BaseModule<String, QuoteViewModel>() {

    private var communication: BaseCommunication<String>? = null

    override fun getViewModel() = QuoteViewModel(getInteractor(), getCommunication())

    override fun getCommunication(): BaseCommunication<String> {
        if(communication == null){
            communication = BaseCommunication()
        }
        return communication!!
    }

    override fun getInteractor() = BaseInteractor(getRepository(), failureHandler,
    CommonSuccessMapper())

    override fun getRepository() = BaseRepository(getCachedDataSource(), getCloudDataSource(),
    BaseCachedData())

    override fun getCachedDataSource()= QuoteCachedDataSource(realmProvider, CommonSuccessMapper.QuoteRealmMapper(),
    QuoteRealmToCommonMapper()
    )

    override fun getCloudDataSource() = QuoteCloudDataSource(retrofit.create(QuoteService::class.java))
}