package com.example.joke_lesson_8.data


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""


    //override fun to() = CommonDataModel(id, text, punchline, true)
}