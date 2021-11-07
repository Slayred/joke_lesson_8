package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.interfaces.CacheDataSource
import com.example.joke_lesson_8.domain.Joke

class TestCacheDataSource: CacheDataSource {

    private val  map = HashMap<Int, Joke>()
    override suspend fun getJoke(): JokeDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel {
        TODO("Not yet implemented")
    }

//    override suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeUIModel {
//        return if (map.containsKey(id)){
//            val  jokeFind = map[id]!!.toBaseJoke()
//            map.remove(id)
//            jokeFind
//        } else{
//            map[id] = joke
//            joke.toFavoriteJoke()
//        }
//    }
//


//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if(map.isEmpty()){
//            jokeCachedCallback.fail()
//        } else jokeCachedCallback.provide(map[0]!!)
//    }
}