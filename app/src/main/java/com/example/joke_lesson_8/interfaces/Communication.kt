package com.example.joke_lesson_8.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.example.joke_lesson_8.presentation.CommonUIModel
import com.example.joke_lesson_8.presentation.State

interface Communication {

    //fun showData( data: Pair<String, Int>)

    fun showState(state: State)
//Move to ListCommunication
//    fun showDataList(list: List<CommonUIModel>)
//
//    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)

    fun observe(owner: LifecycleOwner, observer: Observer<State>)



    fun isState(type: Int): Boolean
    fun getDiffResult(): DiffUtil.DiffResult
}