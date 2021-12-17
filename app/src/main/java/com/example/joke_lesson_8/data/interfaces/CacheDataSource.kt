package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.interfaces.ChangeStatus

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {


}