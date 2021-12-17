package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import io.realm.RealmObject

abstract class DataBaseModel<E>: /*RealmObject(),*/Mapper<CommonDataModel<E>> {
}