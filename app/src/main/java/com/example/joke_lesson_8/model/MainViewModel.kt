package com.example.joke_lesson_8.model

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.interfaces.Model
import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.interfaces.JokeCallback
import com.example.joke_lesson_8.view.EnableView
import com.example.joke_lesson_8.view.ShowImage
import com.example.joke_lesson_8.view.ShowText
import com.example.joke_lesson_8.view.ShowView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val model: Model,
private val communication: Communication,
private val dispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    //private var dataCallback: DataCallback? = null
    //val liveData = MutableLiveData<Pair<String, Int>>()

//    fun initViewModel(callback: DataCallback){
//        this.dataCallback = callback
//        //model.initModel(jokeCallback)
//    }

    fun getJoke() = viewModelScope.launch(dispatcher) {
//        val uiModel = model.getJoke()
//        dataCallback?.let {
//            uiModel.map(it)
//        }
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }



//    fun clear(){
//        dataCallback = null
//        //model.clear()
//    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)

    }

    fun observe(owner: LifecycleOwner, observer: Observer<MainViewModel.State>) = communication.observe(owner, observer)

    fun changeJokeStatus() {
//        model.changeJokeStatus(jokeCallback)
//        viewModelScope.launch {
//            val uiModel = model.changeJokeStatus()
//            dataCallback?.let {
//                uiModel?.map(it)
//            }
//        }
        viewModelScope.launch(dispatcher) {
            model.changeJokeStatus()?.show(communication)
        }
    }
    sealed class State {

        abstract fun show(
            progres: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage

        )

        object Progress: State() {
            override fun show(
                progres: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                progres.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(
                progres: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                progres.show(false)
                button.enable(true)
                textView.show(text)
                imageButton.show(id)
            }
        }
    }

}