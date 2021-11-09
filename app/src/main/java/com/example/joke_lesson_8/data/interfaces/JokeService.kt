package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.JokeDataModel
import com.example.joke_lesson_8.data.JokeServerModel
import com.example.joke_lesson_8.data.NewJokeServerModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeService<T: Mapper<JokeDataModel>> {

    fun getJokeFromAPI(): Call<T>
}

interface BaseJokeService {

    @GET("/jokes/random")
    fun getJokeFromAPI(): Call<JokeServerModel>

}

interface NewJokeService{

    @GET("https://v2.jokeapi.dev/joke/any")
    fun getJokeFromAPI(): Call<NewJokeServerModel>
}





