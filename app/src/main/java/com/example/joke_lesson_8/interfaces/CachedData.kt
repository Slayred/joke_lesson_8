package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface CachedData<E> : ChangeCommonItem<E> {
    fun save(common: CommonDataModel<E>)
    fun clear()
}