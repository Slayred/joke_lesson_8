package com.example.joke_lesson_8.data.net

import com.example.joke_lesson_8.data.CommonDataModel
import com.example.joke_lesson_8.data.CommonDataModelMapper
import com.example.joke_lesson_8.data.RealmToCommonDataMapper
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.domain.NoCachedDataException
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



abstract class BaseCachedDataSource<T: RealmObject,E>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T, E>,
    private val realmToCommonMapper: RealmToCommonDataMapper<T, E>
    ): CacheDataSource<E> {

    protected abstract val dbClass: Class<T>

    protected abstract fun findRealmObject(realm: Realm, id: E): T?

//    private fun getRealmData(): RealmResults<T>{
//        realmProvider.provide().use {
//            val list = it.where(dbClass).findAll()
//            if(list.isEmpty()){
//                throw NoCachedDataException()
//            } else return list
//        }
//    }
private fun <R> getRealmData(block: (list:RealmResults<T>) -> R): R{
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if(list.isEmpty()){
                throw NoCachedDataException()
            } else return block.invoke(list)
        }
    }

//    override suspend fun getData() = realmToCommonMapper.map(getRealmData().random())
//
//    override suspend fun getDataList() = getRealmData().map { realmToCommonMapper.map(it) }
    override suspend fun getData() = getRealmData {
        realmToCommonMapper.map(it.random())
}

    override suspend fun getDataList() = getRealmData {
        results -> results.map{realmToCommonMapper.map(it)}
    }


    override suspend fun addOrRemove(id: E, common: CommonDataModel<E>): CommonDataModel<E> =
        withContext(Dispatchers.IO){
            realmProvider.provide().use {
                val itemRealm = findRealmObject(it, id)  //it.where(dbClass).equalTo("id",id).findFirst()
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