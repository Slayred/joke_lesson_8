package com.example.joke_lesson_8.data

import java.lang.Exception

sealed class Result<out T, out E> {
    data class Success<out T>(val data: T): Result<T, Nothing>()
    data class Error<out S>(val exception: S): Result<Nothing, S>()
}