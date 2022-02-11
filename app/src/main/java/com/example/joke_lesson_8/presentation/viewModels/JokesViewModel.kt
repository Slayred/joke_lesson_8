package com.example.joke_lesson_8.presentation.viewModels

import com.example.joke_lesson_8.domain.BaseInteractor
import com.example.joke_lesson_8.model.BaseCommunication

class JokesViewModel(interactor: BaseInteractor<Int>,
communication: BaseCommunication<Int>): BaseViewModel<Int>(interactor, communication, "Jokes")

class QuoteViewModel(interactor: BaseInteractor<String>,
communication: BaseCommunication<String>): BaseViewModel<String>(interactor, communication, "Quotes")