package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.JokeCachedCallback
import com.example.joke_lesson_8.model.Joke
import com.example.joke_lesson_8.model.JokeRealm
import com.example.joke_lesson_8.model.JokeServerModel
import io.realm.Realm

class BaseRealmCachedDataSource(private val realm: Realm): CacheDataSource {


    override fun addOrRemove(id: Int, joke: Joke): JokeUIModel {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
            return if (jokeRealm == null){
                val newJoke = joke.toJokeRealm()

                it.executeTransactionAsync{
                        transaction ->
                    transaction.insert(newJoke)
                }
                joke.toFavoriteJoke()
            } else {
                it.executeTransactionAsync{ trans ->
                    trans.where(JokeRealm::class.java).equalTo("id",id).findFirst()
                        ?.deleteFromRealm()
                }
                joke.toBaseJoke()

            }
        }
    }

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let{
                val jokes = it.where(JokeRealm::class.java).findAll()
                if(jokes.isEmpty()){
                    jokeCachedCallback.fail()
                } else {
                    jokes.random().let { joke ->
                        jokeCachedCallback.provide(
                            Joke(
                            joke.id,
                            joke.type,
                            joke.text,
                            joke.punchline
                        )
                        )
                    }
                }
        }
    }
}