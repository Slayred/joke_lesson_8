package com.example.joke_lesson_8

import android.view.Display
import android.webkit.SslErrorHandler
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(private val service: JokeService
, private val resourceManager: ResourceManager): Model {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailible by lazy { ServiceUnavailible(resourceManager) }
    private val SSLerror_exc by lazy { SSLError_exception(resourceManager) }
    override fun getJoke() {
        service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO>{
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if(response.isSuccessful){
                    callback?.provideSuccess(response.body()!!.toJoke())
                }
                else callback?.provideError(serviceUnavailible)
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if(t is UnknownHostException)
                    callback?.provideError(noConnection)
                if (t is SslErrorHandler)
                    callback?.provideError(SSLerror_exc)
                else
                    callback?.provideError(serviceUnavailible)
            }

        })
    }

//    override fun getJoke() {
//        service.getJoke(object : ServiceCallback{
//            override fun returnSuccess(data: JokeDTO) {
//                callback?.provideSuccess(data.toJoke())
//            }
//
//            override fun returnError(type: ErrorType) {
//                when(type){
//                    ErrorType.OTHER -> callback?.provideError(serviceUnavailible)
//                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
//                    ErrorType.SLL_ERROR -> callback?.provideError(SSLerror_exc)
//                }
//            }
//
//        })
//}

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }


}