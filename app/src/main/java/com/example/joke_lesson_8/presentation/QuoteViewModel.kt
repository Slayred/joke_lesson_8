package com.example.joke_lesson_8.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.joke_lesson_8.interfaces.Communication

class QuoteViewModel(
    private val communication: Communication
): ViewModel(), CommonViewModel {
    override fun getItem() {
        TODO("Not yet implemented")
    }

    override fun changeStatus() {
        TODO("Not yet implemented")
    }

    override fun chooseFavorites(favorites: Boolean) {
        TODO("Not yet implemented")
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
       communication.observe(owner, observer)
    }
}