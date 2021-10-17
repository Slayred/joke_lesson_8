package com.example.joke_lesson_8.interfaces

import androidx.annotation.StringRes

interface ResourceManager{
    fun getString(@StringRes stringResID: Int): String
}