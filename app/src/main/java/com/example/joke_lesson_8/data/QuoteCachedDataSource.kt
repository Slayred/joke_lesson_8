package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.interfaces.RealmProvider

class QuoteCachedDataSource(realmProvider: RealmProvider, mapper: CommonSuccessMapper.QuoteRealmMapper,
                            commonDataMapper: QuoteRealmToCommonMapper
):
BaseCachedDataSource<QuoteRealmModel>(realmProvider, mapper, commonDataMapper){

    override val dbClass: Class<QuoteRealmModel> = QuoteRealmModel::class.java
}