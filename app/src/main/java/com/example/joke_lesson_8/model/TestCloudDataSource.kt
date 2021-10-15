package com.example.joke_lesson_8.model

import com.example.joke_lesson_8.interfaces.CloudDataSource
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

class TestCloudDataSource: CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(Joke(count,"testType","TestText$count","TestPunchline$count"))
        count++
    }
}