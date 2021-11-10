package com.example.joke_lesson_8.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.joke_lesson_8.R

class FavoriteDataView: LinearLayout {

    private val checkBox: CheckBox
    private val textView: CorrectTextView
    private val changeButton: CorrectImageButton
    private val actionButton: CorrectButton
    private val progressBar: CorrectProgressBar

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        (context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        .inflate(R.layout.favorite_data_view,this,true)

        val linear = getChildAt(1) as LinearLayout
        checkBox = linear.findViewById(R.id.checkBox)
        textView = linear.findViewById(R.id.text)
        changeButton = linear.findViewById(R.id.changeBtn)
        progressBar = linear.findViewById(R.id.progressBar)
        actionButton = linear.findViewById(R.id.btnJoke)

    }

}