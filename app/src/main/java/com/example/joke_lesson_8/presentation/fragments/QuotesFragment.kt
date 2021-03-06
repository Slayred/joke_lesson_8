package com.example.joke_lesson_8.presentation.fragments

import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel
import com.example.joke_lesson_8.presentation.JokeApp
import com.example.joke_lesson_8.presentation.viewModels.QuoteViewModel

class QuotesFragment: BaseFragment<QuoteViewModel,String>() {
    //override fun getViewModel(app: JokeApp): BaseViewModel<String> = app.quoteViewModel

    //override fun getCommunication(app: JokeApp): BaseCommunication<String>  = app.quoteCommunication

    override fun checkBoxText(): Int = R.string.show_favorite_quote

    override fun actionButtonText(): Int  = R.string.get_quote
    override fun getViewModelClass(): Class<QuoteViewModel> = QuoteViewModel::class.java
}