package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.domain.CommonItem

interface CommonIntercator<T> {

    suspend fun getItem(): CommonItem<T>

    suspend fun getItemList(): List<CommonItem<T>>

    suspend fun changeFavourites(): CommonItem<T>

    fun getFavoriteJokes(favorites: Boolean)
}