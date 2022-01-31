package com.example.joke_lesson_8.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.presentation.CommonUIModel

interface ListCommunication<T> {

    fun showDataList(list: List<CommonUIModel<T>>)

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>)

    fun removeItem(id: T, owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>)
}