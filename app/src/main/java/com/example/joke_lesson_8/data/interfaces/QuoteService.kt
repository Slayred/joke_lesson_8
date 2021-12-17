package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.QuoteServerModel
import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {

    @GET("https://api.quotable.io/random")
    fun getQuote(): Call<QuoteServerModel>
}