package com.example.joke_lesson_8.jokeapp

import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.domain.interfaces.FavoriteItemClickListener
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.interfaces.Failure
import com.example.joke_lesson_8.interfaces.ResourceManager
import com.example.joke_lesson_8.presentation.CommonUIModel
import com.example.joke_lesson_8.presentation.State

class BaseCommonUIModel<E>(private var text: String, private val punchline: String) : CommonUIModel<E>(text,punchline) {
//    fun getJokeUiOld() = "$text\n$punchline"
    override fun getIconResId(): Int {
         return R.drawable.baseline_favorite_border_24
    }
}

class FavoriteCommonUIModel<E>(private val id: E, text: String, punchline: String): CommonUIModel<E>(text,punchline){
    override fun getIconResId(): Int {
        return R.drawable.baseline_favorite_24
    }

    override fun change(listener: FavoriteItemClickListener<E>) {
        listener.changeId(id)
    }
}

class FailedCommonUIModel(private val text: String): CommonUIModel<Unit>(text,""){
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(),getIconResId())
    )

}


class NoCached(private val baseResourceManager: ResourceManager): Failure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.no_cached_joke)
    }
}

class NoConnection(private val baseResourceManager: ResourceManager): Failure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.no_connection)
    }
}

class ServiceUnavailible(private val baseResourceManager: ResourceManager): Failure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.service_unavailable)
    }
}

class SSLFailure_exception(private val baseResourceManager: ResourceManager): Failure {
    override fun getMessage(): String {
        return baseResourceManager.getString(R.string.ssl_error)
    }

}