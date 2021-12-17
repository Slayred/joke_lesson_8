package com.example.joke_lesson_8.interfaces

import com.example.joke_lesson_8.data.CommonDataModel
import java.lang.IllegalStateException

interface ChangeCommonItem<E> {
    suspend fun change (changeStatus: ChangeStatus<E>): CommonDataModel<E>

    class Empty<E>: ChangeCommonItem<E> {
        override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
//            return JokeDataModel(0,"", "")
            throw IllegalStateException("empty change item called")
        }

    }
}