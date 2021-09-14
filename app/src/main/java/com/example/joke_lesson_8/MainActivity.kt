package com.example.joke_lesson_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        var btn = findViewById<Button>(R.id.btnJoke)
        var tView = findViewById<TextView>(R.id.tvJoke)
        var progBar = findViewById<ProgressBar>(R.id.progressBar)
        progBar.visibility = View.INVISIBLE

        btn.setOnClickListener{
            btn.isEnabled = false
            progBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallback{
            override fun provideText(test: String) = runOnUiThread {
                btn.isEnabled  = true
                progBar.visibility= View.INVISIBLE
                tView.text = test
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}