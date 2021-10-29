package com.example.joke_lesson_8.jokeapp

import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.data.JokeRepository
import com.example.joke_lesson_8.jokeapp.view.EnableView
import com.example.joke_lesson_8.jokeapp.view.ShowImage
import com.example.joke_lesson_8.jokeapp.view.ShowText
import com.example.joke_lesson_8.jokeapp.view.ShowView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val jokeRepository: JokeRepository,
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
        jokeRepository.getJoke().show(communication)
    }



//    fun clear(){
//        dataCallback = null
//        //model.clear()
//    }

    fun chooseFavorites(favorites: Boolean) {
        jokeRepository.chooseDataSource(favorites)

    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)

    fun changeJokeStatus() {
//        model.changeJokeStatus(jokeCallback)
//        viewModelScope.launch {
//            val uiModel = model.changeJokeStatus()
//            dataCallback?.let {
//                uiModel?.map(it)
//            }
//        }
        viewModelScope.launch(dispatcher) {
            jokeRepository.changeJokeStatus()?.show(communication)
        }
    }
    sealed class State {

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

        object Progress: State() {
            override fun show(
                progress: ShowView,
                button: EnableView
            ) {
                progress.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(
                progress: ShowView,
                button: EnableView,
            ) {
                progress.show(false)
                button.enable(true)
            }

            override fun show(textView: ShowText, imageButton: ShowImage) {
                textView.show(text)
                imageButton.show(id)
            }
        }
    }

}