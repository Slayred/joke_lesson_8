package com.example.joke_lesson_8

import com.example.joke_lesson_8.interfaces.DataCallback


class ViewModel(private val model: Model) {

    private var callback: DataCallback? = null

    fun initViewModel(callback: DataCallback){
        this.callback = callback
        model.initModel(
            object :ResultCallBack{
                override fun provideJoke(joke: Joke) {
                    callback.let {
                        joke.map(it)
                    }
//                    callback.run {//it's mean two separate line for each method
//                        provideIconRes(joke.getIconResId())
//                        provideText(joke.getJokeUi())
//                    }
                }

            }
//            object : ResultCallbackOld {
//            override fun provideSuccess(data: BaseJoke) {
//                callback.provideText(data.getJokeUi())
//                callback.provideIconRes(data.getIconResId())
//            }
//            override fun provideError(error: JokeError) {
//                val joke = FailedJoke(error.getMessage())
//                callback.provideText(joke.getJokeUi())
//                callback.provideIconRes(joke.getIconResId())
//            }
//
//            }
        )
    }

    fun getJoke(){
        model.getJoke()
    }

    fun clear(){
        callback = null
        model.clear()
    }

    fun chooseFavorites(checked: Boolean) {

    }

}
