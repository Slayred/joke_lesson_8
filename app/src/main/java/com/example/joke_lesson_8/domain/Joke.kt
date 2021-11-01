package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.jokeapp.BaseJokeUiModel
import com.example.joke_lesson_8.jokeapp.FailedJokeUIModel
import com.example.joke_lesson_8.jokeapp.FavoriteJokeUIModel
import com.example.joke_lesson_8.jokeapp.JokeUIModel

sealed class Joke : Mapper<JokeUIModel>{
    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ): Joke(){
        override fun to(): JokeUIModel {
            return  if( favorite) {
                FavoriteJokeUIModel(text,punchline)
            } else{
                BaseJokeUiModel(text, punchline)
            }
        }

    }

    class Failed(private val text: String): Joke(){
        override fun to(): JokeUIModel {
            return FailedJokeUIModel(text)
        }

    }
}

//class Joke(
//    private val id: Int,
//    private val type: String,
//    private val text: String,
//    private val punchline: String
//):ChangeJoke {
//
//    //override suspend fun change(changeJokeStatus: ChangeJokeStatus) = changeJokeStatus.addOrRemove(id,this)
//
//    fun toBaseJoke() = BaseJokeUiModel(text,punchline)
//
//    fun toFavoriteJoke() = FavoriteJokeUIModel(text,punchline)
//
//    fun toJokeRealm() : JokeRealmModel {
//        return JokeRealmModel().also {
//            it.id = id
//            it.type = type
//            it.text = text
//            it.punchline = punchline
//        }
//    }
//
//    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel? {
//        TODO("Not yet implemented")
//    }
//}