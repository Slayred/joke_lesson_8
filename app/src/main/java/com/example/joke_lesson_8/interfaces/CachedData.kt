package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface CachedData : ChangeCommonItem {
    fun save(common: CommonDataModel)
    fun clear()
}