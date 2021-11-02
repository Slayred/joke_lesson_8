package com.example.joke_lesson_8.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.jokeapp.MainViewModel

class BaseCommunication: Communication {

    private val liveData = MutableLiveData<MainViewModel.State>()



//    override fun showData(data: Pair<String, Int>) {
//        liveData.value = data
//    }

    override fun showState(state: MainViewModel.State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}