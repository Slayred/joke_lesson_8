package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.interfaces.RealmProvider

class JokeCachedDataSource(realmProvider: RealmProvider, mapper: CommonSuccessMapper.JokeRealmMapper,
                           commonDataMapper: JokeRealmToCommonMapper

):
BaseCachedDataSource<JokeRealmModel>(realmProvider, mapper, commonDataMapper){

    override val dbClass: Class<JokeRealmModel> = JokeRealmModel::class.java
}