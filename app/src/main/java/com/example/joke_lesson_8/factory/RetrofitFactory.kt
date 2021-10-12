package com.example.joke_lesson_8.factory

import com.example.joke_lesson_8.JokeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

  companion object{
      private fun getOkHttpInstance(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
          logging.level = HttpLoggingInterceptor.Level.BASIC
          return OkHttpClient().newBuilder()
              .addInterceptor(logging)
              .build()
      }

      private fun getRetrofitInstance(url: String): Retrofit{
          return Retrofit.Builder()
              .baseUrl(url)
              .client(getOkHttpInstance())
              .addConverterFactory(GsonConverterFactory.create())
              .build()
      }

      fun getService(url: String): JokeService = getRetrofitInstance(url).create(JokeService::class.java)

  }
}