package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.interfaces.ChangeCommonItem
import com.example.joke_lesson_8.interfaces.ChangeStatus
import com.example.joke_lesson_8.presentation.ShowText

class CommonDataModel<E>(
    private val id: E,
    private val firstText: String,
    private val secondText: String,
    private val cached: Boolean = false
): ChangeCommonItem<E> {
    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>  =  changeStatus.addOrRemove(id,this)

    fun map(showText: ShowText) = showText.show(firstText)

    fun <T> map(mapper: CommonDataModelMapper<T, E>): T {
        Log.d("TAG", "Call from CommonDataModel")
        return mapper.map(id,firstText,secondText,cached)

    }

    fun changeCached(cached: Boolean) : CommonDataModel<E>{
        return CommonDataModel(id, firstText, secondText, cached)
    }
}