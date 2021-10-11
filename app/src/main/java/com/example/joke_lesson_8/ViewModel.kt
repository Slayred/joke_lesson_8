package com.example.joke_lesson_8

import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.interfaces.JokeCallback


class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null
    private val jokeCallback = object : JokeCallback{
        override fun provide(joke: Joke) {
            dataCallback?.let {
                joke.map(it)
            }
        }
    }

    fun initViewModel(callback: DataCallback){
        this.dataCallback = callback
        model.initModel(jokeCallback)
    }

    fun getJoke(){
        model.getJoke()
    }

    fun clear(){
        dataCallback = null
        model.clear()
    }

    fun chooseFavorites(checked: Boolean) {

    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

}
