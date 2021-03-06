package com.example.joke_lesson_8.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.example.joke_lesson_8.interfaces.CommonCommunication
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.presentation.CommonUIModel
import com.example.joke_lesson_8.presentation.State
import com.example.joke_lesson_8.presentation.util.CommonDiffUtilCallback

class BaseCommunication<T>: CommonCommunication<T> {

    private val liveData = MutableLiveData<State>()




//    override fun showData(data: Pair<String, Int>) {
//        liveData.value = data
//    }

    override fun showState(state: State) {
        liveData.value = state
    }

    private val listLiveData = MutableLiveData<ArrayList<CommonUIModel<T>>>()
    private lateinit var diffResult: DiffUtil.DiffResult

    override fun showDataList(list: List<CommonUIModel<T>>) {
        val callback = CommonDiffUtilCallback(listLiveData.value ?: emptyList(), list)
        diffResult = DiffUtil.calculateDiff(callback)
        listLiveData.value = ArrayList(list)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel<T>>>) {
        listLiveData.observe(owner, observer)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }

    override fun getDiffResult() = diffResult

    override fun removeItem(id: T): Int {
       val found  = listLiveData.value?.find {
           it.matches(id)
       }
        val position  = listLiveData.value?.indexOf(found)?: -1
        found?.let {
            listLiveData.value?.remove(it)
        }
        return position
    }

    override fun getList(): List<CommonUIModel<T>> {
        return listLiveData.value?: emptyList()
    }

}