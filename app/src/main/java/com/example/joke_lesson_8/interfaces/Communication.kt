package com.example.joke_lesson_8.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.jokeapp.MainViewModel

interface Communication {

    //fun showData( data: Pair<String, Int>)

    fun showState(state: MainViewModel.State)

    fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>)
}