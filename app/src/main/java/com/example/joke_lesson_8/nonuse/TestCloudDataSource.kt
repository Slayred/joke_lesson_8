package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.data.Result
import com.example.joke_lesson_8.interfaces.CloudDataSource
import com.example.joke_lesson_8.data.ErrorType
import com.example.joke_lesson_8.model.JokeServerModel

class TestCloudDataSource: CloudDataSource {
//    private var count = 0
//    override fun getJoke(callback: JokeCloudCallback) {
//        callback.provide(Joke(count,"testType","TestText$count","TestPunchline$count"))
//        count++
//    }

    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        TODO("Not yet implemented")
    }
}