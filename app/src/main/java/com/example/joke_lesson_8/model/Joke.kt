package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.BaseJokeUiModel
import com.example.joke_lesson_8.FavoriteJokeUIModel
import com.example.joke_lesson_8.interfaces.CachedJoke
import com.example.joke_lesson_8.interfaces.ChangeJoke
import com.example.joke_lesson_8.interfaces.ChangeJokeStatus

class Joke(
    private val id: Int,
    private val type: String,
    private val text: String,
    private val punchline: String
):ChangeJoke {

    override suspend fun change(changeJokeStatus: ChangeJokeStatus) = changeJokeStatus.addOrRemove(id,this)

    fun toBaseJoke() = BaseJokeUiModel(text,punchline)

    fun toFavoriteJoke() = FavoriteJokeUIModel(text,punchline)

    fun toJokeRealm() : JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type
            it.text = text
            it.punchline = punchline
        }
    }
}