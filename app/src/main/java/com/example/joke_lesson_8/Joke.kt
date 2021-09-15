package com.example.joke_lesson_8

class Joke(private var text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"
}