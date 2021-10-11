package com.example.joke_lesson_8

import androidx.annotation.DrawableRes
import com.example.joke_lesson_8.interfaces.DataCallback

class BaseJoke(private var text: String, private val punchline: String) : Joke(text,punchline) {
//    fun getJokeUiOld() = "$text\n$punchline"
    override fun getIconResId(): Int {
         return R.drawable.baseline_favorite_border_24
    }
}

class FavoriteJoke(text: String, punchline: String): Joke(text,punchline){
    override fun getIconResId(): Int {
        return R.drawable.baseline_favorite_24
    }

}

class FailedJoke(text: String): Joke(text,""){
    override fun getIconResId(): Int {
        return 0
    }

}



abstract class Joke(private val text: String, private val punchline: String){
    fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId():Int

    fun map(callBack: DataCallback) = callBack.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }

}


interface JokeFailure{
    fun getMessage(): String
}

class NoCachedJoke(private val resourceManager: ResourceManager):JokeFailure{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.no_cached_joke)
    }
}

class NoConnection(private val resourceManager: ResourceManager): JokeFailure{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.no_connection)
    }
}

class ServiceUnavailible(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.service_unavailable)
    }
}

class SSLFailure_exception(private val resourceManager: ResourceManager): JokeFailure{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.ssl_error)
    }

}