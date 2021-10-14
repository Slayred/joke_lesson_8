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
                0 -> callBack?.provideJoke(BaseJokeUiModel("testtext","testPunch"))
                1 -> callBack?.provideJoke(FavoriteJokeUIModel("favoriteJoke","favorite joke punh"))
                2 -> callBack?.provideJoke(FailedJokeUIModel(serviceUnavailible.getMessage()))

            }
            count++
            if (count > 2 ) count = 0
        }.start()
    }


    override fun initModel(callback: ResultCallBack) {
        this.callBack = callback
    }

    override fun clear() {
        callBack = null
    }

}
