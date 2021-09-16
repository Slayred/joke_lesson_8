package com.example.joke_lesson_8

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(private val context: Context):BaseResourceManager {
    override fun getString(stringResID: Int): String {
        return context.getString(stringResID)
    }
}

interface BaseResourceManager{
    fun getString(@StringRes stringResID: Int): String
}