package com.example.joke_lesson_8.model

import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.interfaces.Model
import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.interfaces.JokeCallback
import kotlinx.coroutines.launch


class MainViewModel(private val model: Model,
private val communication: Communication) : ViewModel() {

    //private var dataCallback: DataCallback? = null
    val liveData = MutableLiveData<Pair<String, Int>>()

//    fun initViewModel(callback: DataCallback){
//        this.dataCallback = callback
//        //model.initModel(jokeCallback)
//    }

    fun getJoke() = viewModelScope.launch {
//        val uiModel = model.getJoke()
//        dataCallback?.let {
//            uiModel.map(it)
//        }
        communication.showData(model.getJoke().getData())
    }



//    fun clear(){
//        dataCallback = null
//        //model.clear()
//    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)

    }

    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) = communication.observe(owner, observer)

    fun changeJokeStatus() {
        //model.changeJokeStatus(jokeCallback)
//        viewModelScope.launch {
//            val uiModel = model.changeJokeStatus()
//            dataCallback?.let {
//                uiModel?.map(it)
//            }
//        }
        viewModelScope.launch {
            model.changeJokeStatus()?.let {
                communication.showData(it.getData())
            }
        }
    }

}
