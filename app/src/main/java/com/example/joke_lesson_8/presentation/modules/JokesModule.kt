package com.example.joke_lesson_8.presentation.modules

import com.example.joke_lesson_8.data.BaseRepository
import com.example.joke_lesson_8.data.CommonSuccessMapper
import com.example.joke_lesson_8.data.FailureHandler
import com.example.joke_lesson_8.data.JokeRealmToCommonMapper
import com.example.joke_lesson_8.data.cache.JokeCachedDataSource
import com.example.joke_lesson_8.data.interfaces.NewJokeService
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.data.net.NewJokeCloudDataSource
import com.example.joke_lesson_8.domain.BaseInteractor
import com.example.joke_lesson_8.model.BaseCachedData
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.JokesViewModel
import retrofit2.Retrofit

class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
): BaseModule<Int, JokesViewModel>(){
    private var communication: BaseCommunication<Int>? = null



    override fun getViewModel(): JokesViewModel {
        return JokesViewModel(getInteractor(), getCommunication())
    }


    override fun getCommunication(): BaseCommunication<Int> {
        if(communication == null){
            communication = BaseCommunication()
        }
        return communication!!
    }

    override fun getInteractor() = BaseInteractor(
        getRepository(), failureHandler,
        CommonSuccessMapper()
    )

    override fun getRepository() = BaseRepository(
        getCachedDataSource(), getCloudDataSource(),
        BaseCachedData<Int>()
    )

    override fun getCachedDataSource()= JokeCachedDataSource(
        realmProvider, CommonSuccessMapper.JokeRealmMapper(), JokeRealmToCommonMapper()
    )

    override fun getCloudDataSource() =
        NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))

}