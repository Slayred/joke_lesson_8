package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.BaseJokeUiModel
import com.example.joke_lesson_8.FavoriteJokeUIModel
import com.example.joke_lesson_8.interfaces.CacheDataSource
import com.google.gson.annotations.SerializedName

class JokeServerModel (
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("punchline")
    private val punchline: String

    )
{
    fun toBaseJoke() = BaseJokeUiModel(setup,punchline)

    fun toFavoriteJoke() = FavoriteJokeUIModel(setup,punchline)

    fun toJokeRealm() : JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type
            it.text = setup
            it.punchline = punchline
        }
    }

    fun toJoke() = Joke(id,type,setup,punchline)

    //fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}