package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.Joke
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.example.joke_lesson_8.model.JokeServerModel

class TestCacheDataSource: CacheDataSource {

    private val  map = HashMap<Int, JokeServerModel>()

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        return if (map.containsKey(id)){
            val  joke = map[id]!!.toBaseJoke()
            map.remove(id)
            joke
        } else{
            map[id] = jokeServerModel
            jokeServerModel.toFavoriteJoke()
        }
    }
}