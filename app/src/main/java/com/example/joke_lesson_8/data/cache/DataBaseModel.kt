package com.example.joke_lesson_8.data.cache

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.CommonDataModel

abstract class DataBaseModel<E>: /*RealmObject(),*/Mapper<CommonDataModel<E>> {
}