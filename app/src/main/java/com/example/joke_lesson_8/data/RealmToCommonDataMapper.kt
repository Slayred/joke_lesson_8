package com.example.joke_lesson_8.data

import io.realm.RealmObject

interface RealmToCommonDataMapper<T: RealmObject> {

    fun map(realmObject: T): CommonDataModel

}

class JokeRealmToCommonMapper: RealmToCommonDataMapper<JokeRealmModel>{
    override fun map(realmObject: JokeRealmModel): CommonDataModel {
     return CommonDataModel(realmObject.id, realmObject.text, realmObject.punchline, true)
    }
}

class QuoteRealmToCommonMapper: RealmToCommonDataMapper<QuoteRealmModel>{
    override fun map(realmObject: QuoteRealmModel): CommonDataModel {
        return CommonDataModel(realmObject.id, realmObject.content, realmObject.author, true)
    }

}