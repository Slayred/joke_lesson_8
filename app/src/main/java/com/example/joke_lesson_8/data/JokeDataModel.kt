package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.interfaces.ChangeJoke
import com.example.joke_lesson_8.interfaces.ChangeJokeStatus

class JokeDataModel(
   private val id: Int,
    private val text: String,
    private val punchlinle: String,
    private val cached: Boolean = false
): ChangeJoke {
    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel  =  changeJokeStatus.addOrRemove(id,this)

    fun <T> map(mapper: JokerDataModelMapper<T>): T {
        return mapper.map(id,text,punchlinle,cached)

    }

    fun changeCached(cached: Boolean) : JokeDataModel{
        return JokeDataModel(id, text, punchlinle, cached)
    }
}