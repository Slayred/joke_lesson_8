package com.example.joke_lesson_8.jokeapp

import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.interfaces.JokeFailure
import com.example.joke_lesson_8.interfaces.ResourceManager
import com.example.joke_lesson_8.model.BaseResourceManager

class BaseJokeUiModel(private var text: String, private val punchline: String) : JokeUIModel(text,punchline) {
//    fun getJokeUiOld() = "$text\n$punchline"
    override fun getIconResId(): Int {
         return R.drawable.baseline_favorite_border_24
    }
}

class FavoriteJokeUIModel(text: String, punchline: String): JokeUIModel(text,punchline){
    override fun getIconResId(): Int {
        return R.drawable.baseline_favorite_24
    }

}

class FailedJokeUIModel(text: String): JokeUIModel(text,""){
    override fun getIconResId(): Int {
        return 0
    }

}


class NoCachedJoke(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.no_cached_joke)
    }
}

class NoConnection(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.no_connection)
    }
}

class ServiceUnavailible(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.service_unavailable)
    }
}

class SSLFailure_exception(private val baseResourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.ssl_error)
    }

}