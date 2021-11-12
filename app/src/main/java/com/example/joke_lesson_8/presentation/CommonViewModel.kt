package com.example.joke_lesson_8.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.jokeapp.MainViewModel

interface CommonViewModel {

    fun getItem()
    fun changeStatus()
    fun chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>)
}