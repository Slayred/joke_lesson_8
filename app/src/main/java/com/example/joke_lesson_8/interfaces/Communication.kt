package com.example.joke_lesson_8.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.presentation.CommonUIModel
import com.example.joke_lesson_8.presentation.State

interface Communication {

    //fun showData( data: Pair<String, Int>)

    fun showState(state: State)

    fun showDataList(list: List<CommonUIModel>)

    fun observe(owner: LifecycleOwner, observer: Observer<State>)

    fun isState(type: Int): Boolean
}