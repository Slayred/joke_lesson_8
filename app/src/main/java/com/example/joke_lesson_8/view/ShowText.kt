package com.example.joke_lesson_8.view

import androidx.annotation.DrawableRes

interface ShowText {
    fun show(text: String)
}

interface ShowImage {
    fun show(@DrawableRes id: Int)
}

interface ShowView {
    fun show (show: Boolean)
}

interface EnableView {
    fun enable (enable: Boolean)
}