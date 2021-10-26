package com.example.joke_lesson_8.model

import androidx.annotation.DrawableRes
import com.example.joke_lesson_8.interfaces.Communication

abstract class JokeUIModel(private val text: String, private val punchline: String){
    fun text() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId():Int

//    fun map(callBack: DataCallback) = callBack.run {
//        provideText(getJokeUi())
//        provideIconRes(getIconResId())
//    }

//    fun getData() = Pair(text(),getIconResId())

   // fun show(communication: Communication) = communication.showData(Pair(text(), getIconResId()))
    fun show (communication: Communication) = communication.showState(MainViewModel.State.Initial(text(),getIconResId()))

}

