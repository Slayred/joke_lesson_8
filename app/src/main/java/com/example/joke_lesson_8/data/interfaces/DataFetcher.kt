package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.CommonDataModel

interface DataFetcher<E>{
    suspend fun getData(): CommonDataModel<E>
}