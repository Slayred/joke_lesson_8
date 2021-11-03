package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
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

    ) : Mapper<JokeDataModel>
{
    override fun to() = JokeDataModel(id,setup,punchline)
    //override fun to() = JokeDataModel(id,type,setup,punchline)


//    fun toBaseJoke() = BaseJokeUiModel(setup,punchline)
//
//    fun toFavoriteJoke() = FavoriteJokeUIModel(setup,punchline)
//
//    fun toJokeRealm() : JokeRealm {
//        return JokeRealm().also {
//            it.id = id
//            it.type = type
//            it.text = setup
//            it.punchline = punchline
//        }
//    }
//
//fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}