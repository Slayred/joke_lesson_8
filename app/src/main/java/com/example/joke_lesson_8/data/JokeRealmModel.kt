package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: DataBaseModel() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""


    override fun to() = CommonDataModel(id, text, punchline, true)
}