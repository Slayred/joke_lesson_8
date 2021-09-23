package com.example.joke_lesson_8.source

import com.example.joke_lesson_8.JokeService
import com.example.joke_lesson_8.interfaces.CloudDataSoruce
import com.example.joke_lesson_8.interfaces.JokeCloudCallback

class BaseCloadDataSource(private val service: JokeService): CloudDataSoruce {
    override fun getJoke(callback: JokeCloudCallback) {
        TODO("Not yet implemented")
    }
}