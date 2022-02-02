package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface CommonRepository<E>{

    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun getCommonItemList(): List<CommonDataModel<E>>

    suspend fun changeStatus() : CommonDataModel<E>

    fun chooseDataSource(favorites: Boolean)
    suspend fun remove(id: E)


}










