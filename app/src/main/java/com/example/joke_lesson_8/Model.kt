package com.example.joke_lesson_8

import android.service.carrier.CarrierMessagingService

interface Model {
        fun getJoke()

        fun init(callback: ResultCallback)

        fun clear()
}
interface ResultCallback{

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeError)

}