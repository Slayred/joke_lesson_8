package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.data.CommonDataModel
import com.example.joke_lesson_8.interfaces.CachedData
import com.example.joke_lesson_8.interfaces.ChangeStatus
import com.example.joke_lesson_8.interfaces.ChangeCommonItem

class BaseCachedData: CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()


    override fun save(common: CommonDataModel) {
        cached = common
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}