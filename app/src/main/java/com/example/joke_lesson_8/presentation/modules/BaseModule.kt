package com.example.joke_lesson_8.presentation.modules

import com.example.joke_lesson_8.data.BaseRepository
import com.example.joke_lesson_8.data.interfaces.*
import com.example.joke_lesson_8.domain.BaseInteractor
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel

abstract class BaseModule<E, T: BaseViewModel<E>> {
    abstract fun getViewModel(): T
    abstract fun getCommunication(): BaseCommunication<E>
    abstract fun getInteractor(): BaseInteractor<E>
    abstract fun getRepository(): BaseRepository<E>
    abstract fun getCachedDataSource(): CacheDataSource<E>
    abstract fun getCloudDataSource(): CloudDataSource<E>

}

