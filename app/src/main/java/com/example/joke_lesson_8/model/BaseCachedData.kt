package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.data.CommonDataModel
import com.example.joke_lesson_8.interfaces.CachedData
import com.example.joke_lesson_8.interfaces.ChangeStatus
import com.example.joke_lesson_8.interfaces.ChangeCommonItem

class BaseCachedData<E>: CachedData<E> {
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()


    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}