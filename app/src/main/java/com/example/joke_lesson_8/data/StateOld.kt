package com.example.joke_lesson_8.data

import androidx.annotation.DrawableRes

sealed class StateOld {
    object Progress: StateOld()
    data class Initial(val text: String, @DrawableRes val id: Int)
}