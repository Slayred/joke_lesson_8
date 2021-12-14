package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.domain.CommonItem

interface CommonIntercator {

    suspend fun getItem(): CommonItem

    suspend fun changeFavourites(): CommonItem

    fun getFavoriteJokes(favorites: Boolean)
}