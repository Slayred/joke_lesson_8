package com.example.joke_lesson_8

class TestModel(resourceManager: ResourceManager): Model {

    private var callback: ResultCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailible = ServiceUnavailible(resourceManager)


    override fun getJoke() {
        Thread{
        Thread.sleep(1000)
//        if (count % 2 == 0){
//            callback?.provideSuccess("succes")
//        } else {
//            callback?.provideError("error")
//        }
            when(count){
                0 -> callback?.provideSuccess(Joke("testtext","testPunch"))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailible)

            }
            count++
            if (count > 2 ) count = 0
        }.start()
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}
