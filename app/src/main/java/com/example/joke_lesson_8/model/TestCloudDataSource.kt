package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

class TestCloudDataSource: CloudDataSoruce {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(JokeServerModel(count,"testType","TestText$count","TestPunchline$count"))
        count++
    }
}