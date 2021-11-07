package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.interfaces.ChangeJokeStatus
import com.example.joke_lesson_8.interfaces.ChangeJoke

class BaseCachedJoke: CachedJoke {
    private var cached: ChangeJoke = ChangeJoke.Empty()


    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = ChangeJoke.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return cached.change(changeJokeStatus)
    }
}