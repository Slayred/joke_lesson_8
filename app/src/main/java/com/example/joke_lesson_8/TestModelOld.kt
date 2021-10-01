package com.example.joke_lesson_8

class TestModelOld(resourceManager: ResourceManager): ModelOld {

    private var callBack: ResultCallBack? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailible = ServiceUnavailible(resourceManager)


    override fun getJoke() {
        Thread{
        Thread.sleep(1000)
            when(count){
                0 -> callBack?.provideJoke(BaseJoke("testtext","testPunch"))
                1 -> callBack?.provideJoke(FavoriteJoke("favoriteJoke","favorite joke punh"))
                2 -> callBack?.provideJoke(FailedJoke(serviceUnavailible.getMessage()))

            }
            count++
            if (count > 2 ) count = 0
        }.start()
    }


    override fun initModel(callback: ResultCallBack) {
        this.callBack = callBack
    }

    override fun clear() {
        callBack = null
    }

}
