package com.example.joke_lesson_8.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface CommonViewModel {

    fun getItem()
    fun getItemList()
    fun changeStatus()
    fun  chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}