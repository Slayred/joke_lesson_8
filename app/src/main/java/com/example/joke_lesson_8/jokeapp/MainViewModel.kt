package com.example.joke_lesson_8.jokeapp

import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.data.interfaces.JokeInteractor
import com.example.joke_lesson_8.presentation.EnableView
import com.example.joke_lesson_8.presentation.ShowImage
import com.example.joke_lesson_8.presentation.ShowText
import com.example.joke_lesson_8.presentation.ShowView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val intercator: JokeInteractor,
                    private val communication: Communication,
                    private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {


    fun getJoke() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        intercator.getJoke().to().show(communication)
    }




    fun chooseFavorites(favorites: Boolean) = viewModelScope.launch  {
        intercator.getFavoriteJokes(favorites)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)

    fun changeJokeStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL))
                intercator.changeFavourites().to().show(communication)
        }
    }
    sealed class State {

        protected abstract val type: Int
        companion object {
            const val INITIAL = 0
            const val PROGRESS = 1
            const val FAILED = 2
        }

        fun isType(type: Int): Boolean = this.type == type

        fun show(
            progress: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage

        ) {
            show(progress,button)
            show(textView, imageButton)
        }
        protected open fun show(progress: ShowView, button: EnableView){}
        protected open fun show(textView: ShowText, imageButton: ShowImage) {}

        object Progress : State() {
            override val type = PROGRESS

            override fun show(
                progress: ShowView,
                button: EnableView
            ) {
                progress.show(true)
                button.enable(false)
            }
        }


        abstract class Info(private val text: String, @DrawableRes private val id: Int): State(){
            override fun show(progress: ShowView, button: EnableView) {
                progress.show(false)
                button.enable(true)
            }

            override fun show(textView: ShowText, imageButton: ShowImage) {
                textView.show(text)
                imageButton.show(id)
            }

        }


        class Initial(val text: String, @DrawableRes private val id: Int): Info(text, id) {
            override val type = INITIAL
        }

        class Failed(text: String, @DrawableRes private val id: Int): Info(text, id){
            override val type = FAILED
        }

//         class Initial(val text: String, @DrawableRes val id: Int) : State() {
//            override fun show(
//                progress: ShowView,
//                button: EnableView,
//            ) {
//                progress.show(false)
//                button.enable(true)
//            }
//
//            override fun show(textView: ShowText, imageButton: ShowImage) {
//                textView.show(text)
//                imageButton.show(id)
//            }
//        }
    }

}
