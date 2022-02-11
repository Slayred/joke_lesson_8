package com.example.joke_lesson_8.presentation.modules

import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel
import com.example.joke_lesson_8.presentation.viewModels.JokesViewModel

abstract class BaseModule<E, T: BaseViewModel<E>> {
    abstract fun getViewModel(): T
    abstract fun getCommunication(): BaseCommunication<E>
}

class JokesModule: BaseModule<Int, JokesViewModel>(){
    override fun getViewModel(): JokesViewModel {
        return JokesViewModel(getInteractor(), getCommunication())
    }

    override fun getCommunication(): BaseCommunication<Int> {
        TODO("Not yet implemented")
    }

}