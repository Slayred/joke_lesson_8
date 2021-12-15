package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface CachedCommonItem : ChangeCommonItem {
    fun saveJoke(common: CommonDataModel)
    fun clear()
}