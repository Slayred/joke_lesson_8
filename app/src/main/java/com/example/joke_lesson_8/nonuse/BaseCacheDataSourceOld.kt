package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.JokeUIModel
import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.model.Joke

class BaseCacheDataSourceOld : CacheDataSource {

    private val list = ArrayList<Pair<Int,Joke>>()


    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUIModel {
        val found = list.find {
            it.first == id
        }
        return if (found != null){
            val jokeFind = found.second.toBaseJoke()
            list.remove(found)
            jokeFind
        } else{
            list.add(Pair(id, joke))
            joke.toFavoriteJoke()
        }

    }

    override suspend fun getJoke(): Result<Joke, Unit> {
        TODO("Not yet implemented")
    }


//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if(list.isEmpty()){
//            jokeCachedCallback.fail()
//        } else jokeCachedCallback.provide(list.random().second)
//
//    }
}