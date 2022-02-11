package com.example.joke_lesson_8.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelsFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when {
            modelClass.isAssignableFrom(JokesViewModel::class.java) -> makeJokesViewModel()
            modelClass.isAssignableFrom(QuoteViewModel::class.java) -> makeQuotesViewModel()
            else -> throw IllegalStateException("unknown type of viewModel")
        }
        return viewModel as T
    }
}