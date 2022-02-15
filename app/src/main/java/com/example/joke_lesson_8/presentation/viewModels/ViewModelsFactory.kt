package com.example.joke_lesson_8.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joke_lesson_8.presentation.modules.JokesModule
import com.example.joke_lesson_8.presentation.modules.QuotesModule

class ViewModelsFactory(
    private val jokesModule: JokesModule,
    private val quotesModule: QuotesModule
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when {
            modelClass.isAssignableFrom(JokesViewModel::class.java) -> jokesModule
            modelClass.isAssignableFrom(QuoteViewModel::class.java) -> quotesModule
            else -> throw IllegalStateException("unknown type of viewModel")
        }
        return viewModel.getViewModel() as T
    }
}