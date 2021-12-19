package com.example.joke_lesson_8.data.interfaces

import com.example.joke_lesson_8.data.net.JokeServerModel
import com.example.joke_lesson_8.data.net.NewJokeServerModel
import retrofit2.Call
import retrofit2.http.GET

//interface JokeService<T: Mapper<CommonDataModel>> {
//
//    fun getJokeFromAPI(): Call<T>
//}

interface BaseJokeService {

    @GET("/jokes/random")
    fun getJokeFromAPI(): Call<JokeServerModel>

}

interface NewJokeService{

    @GET("https://v2.jokeapi.dev/joke/any")
    fun getJokeFromAPI(): Call<NewJokeServerModel>
}





