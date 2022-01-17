package com.example.joke_lesson_8.data.cache

import com.example.joke_lesson_8.data.CommonSuccessMapper
import com.example.joke_lesson_8.data.QuoteRealmToCommonMapper
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.data.net.BaseCachedDataSource
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