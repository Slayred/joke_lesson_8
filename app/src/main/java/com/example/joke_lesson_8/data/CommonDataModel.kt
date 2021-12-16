package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.interfaces.ChangeCommonItem
import com.example.joke_lesson_8.interfaces.ChangeStatus

class CommonDataModel(
   private val id: Int,
    private val text: String,
    private val punchlinle: String,
    private val cached: Boolean = false
): ChangeCommonItem {
    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel  =  changeStatus.addOrRemove(id,this)

    fun <T> map(mapper: CommonDataModelMapper<T>): T {
        Log.d("TAG", "Call from CommonDataModel")
        return mapper.map(id,text,punchlinle,cached)

    }

    fun changeCached(cached: Boolean) : CommonDataModel{
        return CommonDataModel(id, text, punchlinle, cached)
    }
}