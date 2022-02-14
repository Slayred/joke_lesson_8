package com.example.joke_lesson_8.presentation.fragments

import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel
import com.example.joke_lesson_8.presentation.JokeApp
import com.example.joke_lesson_8.presentation.viewModels.JokesViewModel

class JokesFragment: BaseFragment<JokesViewModel,Int>() {
    //override fun getViewModel(app: JokeApp): BaseViewModel<Int> = app.baseViewModel

    override fun checkBoxText(): Int  = R.string.show_favorite_joke

    override fun actionButtonText(): Int  = R.string.get_joke

    //override fun getCommunication(app: JokeApp): BaseCommunication<Int>  = app.jokeCommunication

    override fun getViewModelClass(): Class<JokesViewModel> = JokesViewModel::class.java
}