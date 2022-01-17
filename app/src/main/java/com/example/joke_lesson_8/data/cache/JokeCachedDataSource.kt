package com.example.joke_lesson_8.data.cache

import com.example.joke_lesson_8.data.CommonSuccessMapper
import com.example.joke_lesson_8.data.JokeRealmToCommonMapper
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.data.net.BaseCachedDataSource
import io.realm.Realm

class JokeCachedDataSource(realmProvider: RealmProvider, mapper: CommonSuccessMapper.JokeRealmMapper,
                           commonDataMapper: JokeRealmToCommonMapper

):
BaseCachedDataSource<JokeRealmModel, Int>(realmProvider, mapper, commonDataMapper){

    override val dbClass: Class<JokeRealmModel> = JokeRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: Int): JokeRealmModel? {
       return realm.where(dbClass).equalTo("id",id).findFirst()
    }
}