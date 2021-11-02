package com.example.joke_lesson_8.jokeapp

import androidx.annotation.DrawableRes
import com.example.joke_lesson_8.interfaces.Communication

abstract class JokeUIModel(private val text: String, private val punchline: String){
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId():Int

//    fun map(callBack: DataCallback) = callBack.run {
//        provideText(getJokeUi())
//        provideIconRes(getIconResId())
//    }

//    fun getData() = Pair(text(),getIconResId())

   // fun show(communication: Communication) = communication.showData(Pair(text(), getIconResId()))
    open fun show (communication: Communication) = communication.showState(
       MainViewModel.State.Initial(
           text(),
           getIconResId()
       )
   )

}

