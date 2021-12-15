package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel
import java.lang.IllegalStateException

interface ChangeCommonItem {
    suspend fun change (changeStatus: ChangeStatus): CommonDataModel

    class Empty: ChangeCommonItem {
        override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
//            return JokeDataModel(0,"", "")
            throw IllegalStateException("empty change item called")
        }

    }
}