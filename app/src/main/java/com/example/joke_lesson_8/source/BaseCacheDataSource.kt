package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.interfaces.JokeCachedCallback
import com.example.joke_lesson_8.model.JokeServerModel

class BaseCacheDataSource : CacheDataSource {

    private val list = ArrayList<Pair<Int,JokeServerModel>>()


    override fun addOrRemove(id: Int, joke: JokeServerModel): JokeUIModel {
        val found = list.find {
            it.first == id
        }
        return if (found != null){
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        } else{
            list.add(Pair(id, joke))
            joke.toFavoriteJoke()
        }

    }

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if(list.isEmpty()){
            jokeCachedCallback.fail()
        } else jokeCachedCallback.provide(list.random().second)

    }
}