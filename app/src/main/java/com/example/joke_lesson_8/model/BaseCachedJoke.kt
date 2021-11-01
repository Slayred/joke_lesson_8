package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.interfaces.ChangeJokeStatus
import com.example.joke_lesson_8.domain.Joke
import com.example.joke_lesson_8.jokeapp.JokeUIModel

class BaseCachedJoke: CachedJoke {
    private var cachedJoke: Joke? = null

    override fun saveJoke(joke: Joke) {
        cachedJoke = joke
    }

    override fun clear() {
        cachedJoke = null
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUIModel? {
        return cachedJoke?.change(changeJokeStatus)
    }
}