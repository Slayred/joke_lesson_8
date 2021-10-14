package com.example.joke_lesson_8

import com.example.joke_lesson_8.interfaces.JokeCallback

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

    fun chooseDataSource(favorites: Boolean)
}

interface ResultCallbackOld{

    fun provideSuccess(data: JokeUIModel)

    fun provideError(failure: JokeFailure)

}

interface ResultCallBack{

    fun provideJoke(jokeUIModel: JokeUIModel)
}