package com.example.joke_lesson_8

import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class BaseJokeService(private val gson: Gson) {
//
//     fun getJoke(callback: ServiceCallback) {
//        Thread{
//            var connection: HttpURLConnection? = null
//            try{
//                val url = URL(JOKE_URL)
//                connection = url.openConnection() as HttpURLConnection
//                InputStreamReader(BufferedInputStream(connection.inputStream)).use{
//                    val line: String = it.readText()
//                    val dto = gson.fromJson(line, JokeDTO::class.java)
//                    callback.returnSuccess(dto)
//                }
//            }catch (e: Exception){
//                if (e is UnknownHostException)
//                    callback.returnError(ErrorType.NO_CONNECTION)
//                if (e is SSLHandshakeException)
//                    callback.returnError(ErrorType.SLL_ERROR)
//                else callback.returnError(ErrorType.OTHER)
//            }finally {
//                connection?.disconnect()
//            }
//        }.start()
//    }
//
//    private companion object{
//        const val JOKE_URL = "https://yesno.wtf/api"
//    }
}