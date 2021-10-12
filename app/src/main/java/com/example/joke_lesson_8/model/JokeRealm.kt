package com.example.joke_lesson_8.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""
    var type: String = ""
}