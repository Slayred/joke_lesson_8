package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface ChangeStatus {

    suspend fun addOrRemove(id: Int, common: CommonDataModel) : CommonDataModel
}