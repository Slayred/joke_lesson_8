package com.example.joke_lesson_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.joke_lesson_8.interfaces.DataCallback
import com.example.joke_lesson_8.model.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = (application as JokeApp).mainViewModel
        var btn = findViewById<Button>(R.id.btnJoke)
        var tView = findViewById<TextView>(R.id.tvJoke)
        var progBar = findViewById<ProgressBar>(R.id.progressBar)
        progBar.visibility = View.INVISIBLE
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val iconImage = findViewById<ImageButton>(R.id.changeBtn)

        checkBox.setOnCheckedChangeListener{_, isChecked ->
            mainViewModel.chooseFavorites(isChecked)

        }
        iconImage.setOnClickListener {
            mainViewModel.changeJokeStatus()
        }

        btn.setOnClickListener{
            mainViewModel.getJoke()
//            btn.isEnabled = false
//            progBar.visibility = View.VISIBLE
//            mainViewModel.getJoke()
        }

        mainViewModel.observe(this,{ state ->
            when(state){
                MainViewModel.State.Progress -> {
                    btn.isEnabled = false
                    progBar.visibility = View.INVISIBLE
                }
                is MainViewModel.State.Initial -> {
                    btn.isEnabled = true
                    progBar.visibility = View.VISIBLE
                    tView.text = state.text
                    iconImage.setImageResource(state.id)
                }
            }

        })

//
//        mainViewModel.observe(this, {
//            (mainViewModel.State) ->
//            btn.isEnabled = true
//            progBar.visibility = View.INVISIBLE
//            tView.text = text
//            iconImage.setImageResource(drawableResId)
//
//        })

//        mainViewModel.initViewModel(object : DataCallback{
//            override fun provideText(text: String){
//                btn.isEnabled  = true
//                progBar.visibility= View.INVISIBLE
//                tView.text = text
//            }
//
//            override fun provideIconRes(id: Int)  =  runOnUiThread{
//                iconImage.setImageResource(id)
//            }
//        })
    }

//    override fun onDestroy() {
//        mainViewModel.clear()
//        super.onDestroy()
//    }
//        mainViewModel.liveData.observe(this,{
//            (text, drawableResId) ->
//            btn.isEnabled = true
//            progBar.visibility = View.INVISIBLE
//            tView.text = text
//            iconImage.setImageResource(drawableResId)
//        })
}