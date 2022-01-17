package com.example.joke_lesson_8.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.presentation.CommonUIModel

interface ListCommunication {

    fun showDataList(list: List<CommonUIModel>)

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
}