package com.example.joke_lesson_8.factory

import com.example.joke_lesson_8.data.interfaces.RealmProvider
import io.realm.Realm

class BaseRealmProvider: RealmProvider {
    override fun provide(): Realm {
        return Realm.getDefaultInstance()
    }
}