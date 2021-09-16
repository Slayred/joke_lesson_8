package com.example.joke_lesson_8

import org.junit.Test

import org.junit.Assert.*
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val joke = "https://yesno.wtf/api"
        var connection: HttpURLConnection? = null
        try {
            val url = URL(joke)
            connection = url.openConnection() as HttpURLConnection?
            InputStreamReader(BufferedInputStream(connection?.inputStream)).use{
                println(it.readText())
            }
        }catch (e: Exception){
            if (e is SSLHandshakeException)
                println("SSL")
            if (e is UnknownHostException)
                println("HOST")
            else println("ERROR")
        }
    }
}