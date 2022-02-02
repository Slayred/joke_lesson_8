package com.example.joke_lesson_8.presentation

import androidx.annotation.DrawableRes
import com.example.joke_lesson_8.domain.interfaces.FavoriteItemClickListener
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.presentation.State

abstract class CommonUIModel<T>(private val first: String, private val second: String){
    protected open fun text() = "$first\n$second"

    open fun change(listener: FavoriteItemClickListener<T>) = Unit

    @DrawableRes
    abstract fun getIconResId():Int

    fun show(showText: ShowText) = showText.show(text())

    open fun show (communication: Communication) = communication.showState(
       State.Initial(
           text(),
           getIconResId()
       )
   )

    open fun matches(id: T): Boolean = false

}

