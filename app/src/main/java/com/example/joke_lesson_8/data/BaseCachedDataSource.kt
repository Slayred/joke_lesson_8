package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.domain.NoCachedDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



abstract class BaseCachedDataSource<T: DataBaseModel>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>
    ): CacheDataSource {

    protected abstract val dbClass: Class<T>

    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use{
            val list = it.where(dbClass).findAll()
            if(list.isEmpty()){
                throw NoCachedDataException()
            } else return  list.random().to()
        }
    }


    override suspend fun addOrRemove(id: Int, common: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO){
            realmProvider.provide().use {
                val itemRealm = it.where(dbClass).equalTo("id",id).findFirst()
                return@withContext if (itemRealm == null){
                    it.executeTransaction{
                        transaction ->
                        val newData = common.map(mapper)
                        transaction.insert(newData)
                    }
                    common.changeCached(true)
                } else{
                    it.executeTransaction{
                        itemRealm.deleteFromRealm()
                    }
                    common.changeCached(false)
                }
            }
        }



}