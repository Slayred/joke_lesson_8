package com.example.joke_lesson_8

import androidx.lifecycle.ViewModel
import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.interfaces.JokeCallback


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
        model.initModel(jokeCallback)
    }

    suspend fun getJoke(){
        model.getJoke()
    }

    fun clear(){
        dataCallback = null
        model.clear()
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)

    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

}
