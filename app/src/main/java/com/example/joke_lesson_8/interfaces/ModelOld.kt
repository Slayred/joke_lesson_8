package com.example.joke_lesson_8

import com.example.joke_lesson_8.interfaces.JokeCallback
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

interface ModelOld {
        fun getJoke()

        fun initModel(callback: ResultCallBack)


        fun clear()
}

interface Model{

    fun getJoke()

    fun initModel(callback: JokeCallback)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun clear()
}

interface ResultCallbackOld{

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeError)

}

interface ResultCallBack{

    fun provideJoke(joke: Joke)
}