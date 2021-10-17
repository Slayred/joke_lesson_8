package com.example.joke_lesson_8.factory

import com.example.joke_lesson_8.interfaces.RealmProvider
import io.realm.Realm

class BaseRealmProvider: RealmProvider {
    override fun provide(): Realm {
        return Realm.getDefaultInstance()
    }
}