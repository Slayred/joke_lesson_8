package com.example.joke_lesson_8.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.presentation.CommonUIModel
import com.example.joke_lesson_8.presentation.State

class BaseCommunication: Communication {

    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<List<CommonUIModel>>()



//    override fun showData(data: Pair<String, Int>) {
//        liveData.value = data
//    }

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUIModel>) {
        listLiveData.value = list
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}