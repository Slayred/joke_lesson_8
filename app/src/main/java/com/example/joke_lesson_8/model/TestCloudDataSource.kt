package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

class TestCloudDataSource: CloudDataSoruce {
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(JokeServerModel(0,"testType","TestText","TestPunchline"))
    }
}