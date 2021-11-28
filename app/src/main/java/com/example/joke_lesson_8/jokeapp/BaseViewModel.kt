package com.example.joke_lesson_8.jokeapp

import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.data.interfaces.JokeInteractor
import com.example.joke_lesson_8.presentation.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel(private val intercator: JokeInteractor,
                    private val communication: Communication,
                    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel(), CommonViewModel {


//    fun getJoke() = viewModelScope.launch(dispatcher) {
//        communication.showState(State.Progress)
//        intercator.getJoke().to().show(communication)
//    }

    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            intercator.getJoke().to().show(communication)
        }
    }

    override fun changeStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL))
                intercator.changeFavourites().to().show(communication)
        }
    }


    override suspend fun chooseFavorites(favorites: Boolean) =  intercator.getFavoriteJokes(favorites)


    override  fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)


}
