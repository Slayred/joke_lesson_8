package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.RealmProvider
import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeRealm
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BaseCachedDataSource(private val realmProvider: RealmProvider): CacheDataSource {


    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel =
        //region wo coroutines
//        realm.let {
//            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
//            return if (jokeRealm == null){
//                val newJoke = joke.toJokeRealm()
//
//                it.executeTransactionAsync{
//                        transaction ->
//                    transaction.insert(newJoke)
//                }
//                joke.toFavoriteJoke()
//            } else {
//                it.executeTransactionAsync{ trans ->
//                    trans.where(JokeRealm::class.java).equalTo("id",id).findFirst()
//                        ?.deleteFromRealm()
//                }
//                joke.toBaseJoke()
//
//            }
//        }
        //endregion
        withContext(Dispatchers.IO){
            realmProvider.provide().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
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



    override suspend fun getJoke(): Result<Joke, Unit> {
        realmProvider.provide().let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if(jokes.isEmpty()){
                return Result.Error(Unit)
            } else jokes.random().let {
                joke ->
                return Result.Success(
                    Joke(
                        joke.id, joke.type, joke.text, joke.punchline
                    )
                )
            }

        }
    }

//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        realm.let{
//                val jokes = it.where(JokeRealm::class.java).findAll()
//                if(jokes.isEmpty()){
//                    jokeCachedCallback.fail()
//                } else {
//                    jokes.random().let { joke ->
//                        jokeCachedCallback.provide(
//                            Joke(
//                            joke.id,
//                            joke.type,
//                            joke.text,
//                            joke.punchline
//                        )
//                        )
//                    }
//                }
//        }
//    }
}