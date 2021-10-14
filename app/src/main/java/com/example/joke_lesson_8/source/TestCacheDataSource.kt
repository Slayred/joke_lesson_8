package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.JokeCachedCallback
import com.example.joke_lesson_8.model.JokeServerModel

class TestCacheDataSource: CacheDataSource {

    private val  map = HashMap<Int, JokeServerModel>()

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): JokeUIModel {
        return if (map.containsKey(id)){
            val  joke = map[id]!!.toBaseJoke()
            map.remove(id)
            joke
        } else{
            map[id] = jokeServerModel
            jokeServerModel.toFavoriteJoke()
        }
    }

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if(map.isEmpty()){
            jokeCachedCallback.fail()
        } else jokeCachedCallback.provide(map[0]!!)
    }
}