package com.example.joke_lesson_8

interface Model {
        fun getJoke()

        fun initModel(callback: ResultCallBack)
        //fun initModel(callbackOld: ResultCallbackOld)

        fun clear()
}
interface ResultCallbackOld{

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeError)

}

interface ResultCallBack{

    fun provideJoke(joke: Joke)
}