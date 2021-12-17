package com.example.joke_lesson_8.data

interface CommonRepository<E>{

    suspend fun getCommonItem(): CommonDataModel<E>

    suspend fun changeStatus() : CommonDataModel<E>

    fun chooseDataSource(favorites: Boolean)



}










