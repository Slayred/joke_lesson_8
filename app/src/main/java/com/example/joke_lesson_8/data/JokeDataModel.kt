package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.interfaces.ChangeJoke
import com.example.joke_lesson_8.interfaces.ChangeJokeStatus

class JokeDataModel(
   private val id: Int,
    val text: String,
    val punchlinle: String,
    val cached: Boolean = false
): ChangeJoke {
    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return  changeJokeStatus.addOrRemove(id,this)
    }

    fun toRealm() = JokeRealmModel().also {
        joke ->
        joke.id = id
        joke.text = text
        joke.punchline = punchlinle
    }

    fun changeCached(cached: Boolean) : JokeDataModel{
        return JokeDataModel(id, text, punchlinle, cached)
    }
}