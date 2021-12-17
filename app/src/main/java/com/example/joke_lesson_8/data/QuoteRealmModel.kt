package com.example.joke_lesson_8.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel: RealmObject() {
    @PrimaryKey
    var id: String = ""
    var content: String = ""
    var author: String = ""


    //override fun to() = CommonDataModel(id, content, author, true)
}