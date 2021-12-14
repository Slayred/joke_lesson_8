package com.example.joke_lesson_8.jokeapp

import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import com.example.joke_lesson_8.presentation.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel(private val intercator: CommonIntercator,
                    private val communication: Communication,
                    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel(), CommonViewModel {


    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            intercator.getItem().to().show(communication)
        }
    }

    override fun changeStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL))
                intercator.changeFavourites().to().show(communication)
        }
    }


    override fun chooseFavorites(favorites: Boolean) =  intercator.getFavoriteJokes(favorites)


    override  fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)


}
