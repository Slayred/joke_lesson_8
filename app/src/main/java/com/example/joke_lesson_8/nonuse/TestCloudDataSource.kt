package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.data.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.ErrorType
import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.JokeServerModel

class TestCloudDataSource: CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
        TODO("Not yet implemented")
    }
//    private var count = 0
//    override fun getJoke(callback: JokeCloudCallback) {
//        callback.provide(Joke(count,"testType","TestText$count","TestPunchline$count"))
//        count++
//    }


}