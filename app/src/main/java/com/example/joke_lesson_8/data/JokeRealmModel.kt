package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""
    //var type: String = ""

    //fun toJokeDataModel() = JokeDataModel(id,type, text, punchline)
    override fun to() = JokeDataModel(id, text, punchline, true)
}