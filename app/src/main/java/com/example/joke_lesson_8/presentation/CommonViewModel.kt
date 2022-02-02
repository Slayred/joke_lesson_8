package com.example.joke_lesson_8.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface CommonViewModel<T>: CommonItemViewModel {

    //fun getItem()
    //fun getItemList()
    //fun changeItemStatus()
    fun changeItemStatus(id: T)
    //fun chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>)
}

interface CommonItemViewModel {
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun chooseFavorites(favorites: Boolean)
}