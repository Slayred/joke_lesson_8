package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.cache.JokeRealmModel
import com.example.joke_lesson_8.data.cache.QuoteRealmModel
import io.realm.RealmObject

interface RealmToCommonDataMapper<T: RealmObject,E> {

    fun map(realmObject: T): CommonDataModel<E>

}

class JokeRealmToCommonMapper: RealmToCommonDataMapper<JokeRealmModel, Int>{
    override fun map(realmObject: JokeRealmModel): CommonDataModel<Int> {
     return CommonDataModel(realmObject.id, realmObject.text, realmObject.punchline, true)
    }
}

class QuoteRealmToCommonMapper: RealmToCommonDataMapper<QuoteRealmModel, String>{
    override fun map(realmObject: QuoteRealmModel): CommonDataModel<String> {
        return CommonDataModel(realmObject.id, realmObject.content, realmObject.author, true)
    }

}