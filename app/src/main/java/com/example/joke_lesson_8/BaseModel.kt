package com.example.joke_lesson_8

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

 class BaseModel(
    private val service: JokeService,
    private val resourceManager: ResourceManager): Model {
    private var callbackOld: ResultCallbackOld? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailible by lazy { ServiceUnavailible(resourceManager) }
    private val SSLerror_exc by lazy { SSLError_exception(resourceManager) }
    override fun getJoke() {
        service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO>{
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if(response.isSuccessful){
                    callbackOld?.provideSuccess(response.body()!!.toJoke())
                }
                else callbackOld?.provideError(serviceUnavailible)
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if(t is UnknownHostException)
                    callbackOld?.provideError(noConnection)
                if (t is SSLHandshakeException)
                    callbackOld?.provideError(SSLerror_exc)
                else
                    callbackOld?.provideError(serviceUnavailible)
            }

        })
    }

    override fun initModel(callback: ResultCallBack) {
        this.callbackOld = callbackOld
    }

    override fun clear() {
        callbackOld = null
    }


}