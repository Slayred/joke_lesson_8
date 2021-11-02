package com.example.joke_lesson_8.domain

import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.interfaces.ResourceManager

class GenericError(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.generic_fail_messages)
    }
}