package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.*
import com.example.joke_lesson_8.interfaces.ResultCallBack
import com.example.joke_lesson_8.model.BaseResourceManager
import com.example.joke_lesson_8.service.JokeService

class BaseModelOldOld(
     private val service: JokeService,
     private val baseResourceManager: BaseResourceManager
 ): ModelOld {

    private var callbackOld: ResultCallbackOld? = null
    private val noConnection by lazy { NoConnection(baseResourceManager) }
    private val serviceUnavailible by lazy { ServiceUnavailible(baseResourceManager) }
    private val SSLerror_exc by lazy { SSLFailure_exception(baseResourceManager) }
    override fun getJoke() {
//        service.getJoke().enqueue(object : retrofit2.Callback<JokeServerModel>{
//            override fun onResponse(call: Call<JokeServerModel>, response: Response<JokeServerModel>) {
//                if(response.isSuccessful){
//                    callbackOld?.provideSuccess(response.body()!!.toBaseJoke())
//                }
//                else callbackOld?.provideError(serviceUnavailible)
//            }
//
//            override fun onFailure(call: Call<JokeServerModel>, t: Throwable) {
//                if(t is UnknownHostException)
//                    callbackOld?.provideError(noConnection)
//                if (t is SSLHandshakeException)
//                    callbackOld?.provideError(SSLerror_exc)
//                else
//                    callbackOld?.provideError(serviceUnavailible)
//            }
//
//        })
    }

    override fun initModel(callback: ResultCallBack) {
        this.callbackOld = callbackOld
    }

    override fun clear() {
        callbackOld = null
    }


}