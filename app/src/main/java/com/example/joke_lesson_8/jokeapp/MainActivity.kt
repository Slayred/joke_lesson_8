package com.example.joke_lesson_8.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.jokeapp.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = (application as JokeApp).mainViewModel
        var btn = findViewById<CorrectButton>(R.id.btnJoke)
        var tView = findViewById<CorrectTextView>(R.id.tvJoke)
        var progBar = findViewById<CorrectProgressBar>(R.id.progressBar)
        progBar.visibility = View.INVISIBLE
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val iconImage = findViewById<CorrectImageButton>(R.id.changeBtn)

        checkBox.setOnCheckedChangeListener{_, isChecked ->
            mainViewModel.chooseFavorites(isChecked)

        }
        iconImage.setOnClickListener {
            mainViewModel.changeJokeStatus()
        }

        btn.setOnClickListener{
            mainViewModel.getJoke()

        }

        mainViewModel.observe(this,{ state ->
            state.show(progBar, btn,tView,iconImage)


        })


    }

}