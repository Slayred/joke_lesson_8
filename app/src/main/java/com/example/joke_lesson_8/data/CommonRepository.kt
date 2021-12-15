package com.example.joke_lesson_8.data

interface CommonRepository{

    suspend fun getCommonItem(): CommonDataModel

    suspend fun changeStatus() : CommonDataModel

    fun chooseDataSource(favorites: Boolean)



}










