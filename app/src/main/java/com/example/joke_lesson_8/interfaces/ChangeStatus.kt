package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface ChangeStatus<E> {

    suspend fun addOrRemove(id: E, common: CommonDataModel<E>) : CommonDataModel<E>
}