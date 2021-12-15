package com.example.joke_lesson_8.data

import io.realm.annotations.PrimaryKey

open class QuoteRealmModel: DataBaseModel() {
    @PrimaryKey
    var id: Int = -1
    var content: String = ""
    var author: String = ""


    override fun to(): CommonDataModel = CommonDataModel(id, content, author, true)
}