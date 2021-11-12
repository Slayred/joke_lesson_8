package com.example.joke_lesson_8.jokeapp

import androidx.annotation.DrawableRes
import com.example.joke_lesson_8.interfaces.Communication

abstract class CommonUIModel(private val text: String, private val punchline: String){
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId():Int

    open fun show (communication: Communication) = communication.showState(
       MainViewModel.State.Initial(
           text(),
           getIconResId()
       )
   )

}

