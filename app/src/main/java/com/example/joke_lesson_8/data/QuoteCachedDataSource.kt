package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.interfaces.RealmProvider
import io.realm.Realm

class QuoteCachedDataSource(realmProvider: RealmProvider, mapper: CommonSuccessMapper.QuoteRealmMapper,
                            commonDataMapper: QuoteRealmToCommonMapper
):
BaseCachedDataSource<QuoteRealmModel, String>(realmProvider, mapper, commonDataMapper){

    override val dbClass: Class<QuoteRealmModel> = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: String): QuoteRealmModel? {
        return realm.where(dbClass).equalTo("id", id).findFirst()
    }
}