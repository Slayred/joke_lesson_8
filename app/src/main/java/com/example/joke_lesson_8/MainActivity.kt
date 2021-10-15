package com.example.joke_lesson_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.joke_lesson_8.interfaces.DataCallback


class MainActivity : AppCompatActivity() {

    private lateinit var viewModelWork: ViewModelWork


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelWork = (application as JokeApp).viewModelWork
        var btn = findViewById<Button>(R.id.btnJoke)
        var tView = findViewById<TextView>(R.id.tvJoke)
        var progBar = findViewById<ProgressBar>(R.id.progressBar)
        progBar.visibility = View.INVISIBLE
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val iconImage = findViewById<ImageButton>(R.id.changeBtn)

        checkBox.setOnCheckedChangeListener{_, isChecked ->
            viewModelWork.chooseFavorites(isChecked)

        }
        iconImage.setOnClickListener {
            viewModelWork.changeJokeStatus()
        }

        btn.setOnClickListener{
            btn.isEnabled = false
            progBar.visibility = View.VISIBLE
            viewModelWork.getJoke()
        }

        viewModelWork.initViewModel(object : DataCallback{
            override fun provideText(text: String) = runOnUiThread {
                btn.isEnabled  = true
                progBar.visibility= View.INVISIBLE
                tView.text = text
            }

            override fun provideIconRes(id: Int)  =  runOnUiThread{
                iconImage.setImageResource(id)
            }
        })
    }

    override fun onDestroy() {
        viewModelWork.clear()
        super.onDestroy()
    }
}