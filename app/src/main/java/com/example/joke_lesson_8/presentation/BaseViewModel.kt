package com.example.joke_lesson_8.presentation

import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.data.interfaces.CommonIntercator
import com.example.joke_lesson_8.interfaces.CommonCommunication
import com.example.joke_lesson_8.presentation.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel<T>(private val intercator: CommonIntercator<T>,
                    private val communication: CommonCommunication<T>,
                    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel(), CommonViewModel<T> {


    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            intercator.getItem().to().show(communication)
        }
    }

    override fun getItemList() {
        viewModelScope.launch(dispatcher){
            communication.showDataList(intercator.getItemList().map{it.to()})
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                intercator.changeFavourites().to().show(communication)
                communication.showDataList(intercator.getItemList().map {
                    it.to()
                })
            }
        }
    }

    override fun changeItemStatus(id: T
    ,owner: LifecycleOwner
    ,observer: Observer<List<CommonUIModel<T>>>) {
        communication.removeItem(id, owner, observer)
        viewModelScope.launch(dispatcher) {
            //intercator.removeItem(id)
        }
    }


    override fun chooseFavorites(favorites: Boolean) =  intercator.getFavoriteJokes(favorites)


    override  fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)
    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>) {
        communication.observeList(owner, observer)
    }


}
