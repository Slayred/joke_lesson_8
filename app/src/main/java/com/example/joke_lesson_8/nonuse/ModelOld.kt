package com.example.joke_lesson_8.nonuse

import com.example.joke_lesson_8.interfaces.ResultCallBack

interface ModelOld {
        fun getJoke()

        fun initModel(callback: ResultCallBack)


        fun clear()
}

