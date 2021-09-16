package com.example.joke_lesson_8

class Joke(private var text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"
}

interface JokeError{
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager): JokeError{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.no_connection)
    }
}

class ServiceUnavailible(private val resourceManager: ResourceManager): JokeError {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.service_unavailable)
    }
}

class SSLError_exception(private val resourceManager: ResourceManager): JokeError{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.ssl_error)
    }

}