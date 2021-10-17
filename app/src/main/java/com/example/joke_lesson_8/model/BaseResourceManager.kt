package com.example.joke_lesson_8.model

import android.content.Context
import com.example.joke_lesson_8.interfaces.ResourceManager

class BaseResourceManager(private val context: Context): ResourceManager {
    override fun getString(stringResID: Int): String {
        return context.getString(stringResID)
    }
}

