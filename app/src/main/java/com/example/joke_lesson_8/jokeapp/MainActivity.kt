package com.example.joke_lesson_8.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.presentation.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = (application as JokeApp).mainViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.showJoke)


        favoriteDataView.listenChanges { isChecked -> mainViewModel.chooseFavorites(isChecked) }

        favoriteDataView.handleActionButton { mainViewModel.getJoke() }

        favoriteDataView.handleChangeButton { mainViewModel.changeJokeStatus() }

        mainViewModel.observe(this, {state ->
            favoriteDataView.show(state)
        })



    }

}