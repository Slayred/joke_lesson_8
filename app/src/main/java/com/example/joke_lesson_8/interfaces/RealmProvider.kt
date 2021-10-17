package com.example.joke_lesson_8.interfaces

import io.realm.Realm

interface RealmProvider {

    fun provide(): Realm
}