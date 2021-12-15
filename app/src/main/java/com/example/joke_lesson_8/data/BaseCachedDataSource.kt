package com.example.joke_lesson_8.data

import android.util.Log
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.domain.NoCachedJokesException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BaseCachedDataSource(private val realmProvider: RealmProvider,
private val mapper: CommonDataModelMapper<JokeRealmModel>): CacheDataSource {

    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use{
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if(jokes.isEmpty()){
                throw NoCachedJokesException()
            } else return  jokes.random().to()
        }
    }


    override suspend fun addOrRemove(id: Int, common: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO){
            realmProvider.provide().use {
               Log.d("Tag", "id: $id")
                val jokeRealm = it.where(JokeRealmModel::class.java).equalTo("id",id).findFirst()
                return@withContext if (jokeRealm == null){
                    it.executeTransaction{
                        transaction ->
                        val newJoke = common.map(mapper)
                        transaction.insert(newJoke)
                    }
                    common.changeCached(true)
                } else{
                    it.executeTransaction{
                        jokeRealm.deleteFromRealm()
                    }
                    common.changeCached(false)
                }
            }
        }



//    override suspend fun getJoke(): Result<Joke, Unit> {
//        realmProvider.provide().let {
//            val jokes = it.where(JokeRealmModel::class.java).findAll()
//            if(jokes.isEmpty()){
//                return Result.Error(Unit)
//            } else jokes.random().let {
//                joke ->
//                return Result.Success(
//                    Joke(
//                        joke.id, joke.type, joke.text, joke.punchline
//                    )
//                )
//            }
//
//        }
//    }

}