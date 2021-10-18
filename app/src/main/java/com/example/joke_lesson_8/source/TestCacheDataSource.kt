package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.model.JokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.model.Joke

class TestCacheDataSource: CacheDataSource {

    private val  map = HashMap<Int, Joke>()

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel {
        return if (map.containsKey(id)){
            val  jokeFind = map[id]!!.toBaseJoke()
            map.remove(id)
            jokeFind
        } else{
            map[id] = joke
            joke.toFavoriteJoke()
        }
    }

    override suspend fun getJoke(): Result<Joke, Unit> {
        TODO("Not yet implemented")
    }


//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if(map.isEmpty()){
//            jokeCachedCallback.fail()
//        } else jokeCachedCallback.provide(map[0]!!)
//    }
}