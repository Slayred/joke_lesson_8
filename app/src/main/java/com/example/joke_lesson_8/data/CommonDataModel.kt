package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.interfaces.ChangeCommonItem
import com.example.joke_lesson_8.interfaces.ChangeStatus

class CommonDataModel<E>(
   private val id: E,
    private val text: String,
    private val punchlinle: String,
    private val cached: Boolean = false
): ChangeCommonItem<E> {
    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>  =  changeStatus.addOrRemove(id,this)

    fun <T> map(mapper: CommonDataModelMapper<T, E>): T {
        Log.d("TAG", "Call from CommonDataModel")
        return mapper.map(id,text,punchlinle,cached)

    }

    fun changeCached(cached: Boolean) : CommonDataModel<E>{
        return CommonDataModel(id, text, punchlinle, cached)
    }
}