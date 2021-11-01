package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.data.interfaces.RealmProvider
import com.example.joke_lesson_8.domain.NoCachedJokesException
import com.example.joke_lesson_8.jokeapp.JokeUIModel
import com.example.joke_lesson_8.domain.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BaseCachedDataSource(private val realmProvider: RealmProvider): CacheDataSource {

    override suspend fun getJoke(): JokeDataModel {
        realmProvider.provide().use{
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if(jokes.isEmpty()){
                throw NoCachedJokesException()
            } else return  jokes.random().to()
        }
    }


    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel =
        withContext(Dispatchers.IO){
            realmProvider.provide().use {
                val jokeRealm = it.where(JokeRealmModel::class.java).equalTo("id",id).findFirst()
                return@withContext if (jokeRealm == null){
                    it.executeTransaction{
                        transaction ->
                        val newJoke = joke.toJokeRealm()
                        transaction.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else{
                    it.executeTransaction{
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toBaseJoke()
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