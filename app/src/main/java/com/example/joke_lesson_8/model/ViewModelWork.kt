package com.example.joke_lesson_8.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joke_lesson_8.interfaces.Model
import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.interfaces.JokeCallback
import kotlinx.coroutines.launch


class ViewModelWork(private val model: Model) : ViewModel() {

    private var dataCallback: DataCallback? = null
    private val jokeCallback = object : JokeCallback{
        override fun provide(jokeUIModel: JokeUIModel) {
            dataCallback?.let {
                jokeUIModel.map(it)
            }
        }
    }

    fun initViewModel(callback: DataCallback){
        this.dataCallback = callback
        //model.initModel(jokeCallback)
    }

    fun getJoke() = viewModelScope.launch {
        val uiModel = model.getJoke()
        dataCallback?.let {
            uiModel.map(it)
        }
    }



    fun clear(){
        dataCallback = null
        //model.clear()
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)

    }

    fun changeJokeStatus() {
        //model.changeJokeStatus(jokeCallback)
        viewModelScope.launch {
            val uiModel = model.changeJokeStatus()
            dataCallback?.let {
                uiModel?.map(it)
            }
        }
    }

}
