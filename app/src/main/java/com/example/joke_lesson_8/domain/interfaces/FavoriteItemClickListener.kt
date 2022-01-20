package com.example.joke_lesson_8.domain.interfaces

interface FavoriteItemClickListener <T> {
    fun changeId(id: T)
}