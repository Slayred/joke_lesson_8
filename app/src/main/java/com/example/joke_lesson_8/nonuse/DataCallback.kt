package com.example.joke_lesson_8.nonuse

import androidx.annotation.DrawableRes

interface DataCallback {

    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}