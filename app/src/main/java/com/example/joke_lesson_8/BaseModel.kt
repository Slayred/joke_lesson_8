package com.example.joke_lesson_8

import android.view.Display

class BaseModel(private val service: JokeService
, private val resourceManager: ResourceManager): Model {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailible by lazy { ServiceUnavailible(resourceManager) }
    private val SSLerror_exc by lazy { SSLError_exception(resourceManager) }

    override fun getJoke() {
        service.getJoke(object : ServiceCallback{
            override fun returnSuccess(data: String) {
                callback?.provideSuccess(Joke(data,""))
            }

            override fun returnError(type: ErrorType) {
                when(type){
                    ErrorType.OTHER -> callback?.provideError(serviceUnavailible)
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                    ErrorType.SLL_ERROR -> callback?.provideError(SSLerror_exc)
                }
            }

        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }


}