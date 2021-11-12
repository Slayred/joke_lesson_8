package com.example.joke_lesson_8.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.jokeapp.MainViewModel
import io.realm.Realm.init

class FavoriteDataView: LinearLayout {

    private val checkBox: CheckBox
    private val textView: CorrectTextView
    private val changeButton: CorrectImageButton
    private val actionButton: CorrectButton
    private val progressBar: CorrectProgressBar

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init(attrs)
    }

    init {
        orientation = VERTICAL
        (context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        .inflate(R.layout.favorite_data_view,this,true)

        val linear = getChildAt(1) as LinearLayout
        //checkBox = linear.findViewById(R.id.checkBox)
        checkBox = getChildAt(0) as CheckBox
        //textView = linear.findViewById(R.id.tvJoke)
        //changeButton = linear.findViewById(R.id.changeBtn)
        textView = linear.findViewById(R.id.tvJoke)
        changeButton = linear.findViewById(R.id.changeBtn)
//        progressBar = linear.findViewById(R.id.progressBar)
//        actionButton = linear.findViewById(R.id.btnJoke)
        progressBar = getChildAt(2) as CorrectProgressBar
        progressBar.visibility = INVISIBLE
        actionButton = getChildAt(3) as CorrectButton

    }


    fun listenChanges(block: (checked: Boolean) -> Unit) =
        checkBox.setOnCheckedChangeListener{_, isChecked->
            block.invoke(isChecked)
        }

    fun handleChangeButton(block: () -> Unit) = changeButton.setOnClickListener {
        block.invoke()
    }
    fun handleActionButton(block: () -> Unit) = actionButton.setOnClickListener {
        block.invoke()
    }

    fun show(state: MainViewModel.State) = state.show(progressBar,actionButton,textView,changeButton)

  private fun init(attrs: AttributeSet){
        context.theme.obtainStyledAttributes(attrs,R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtontext = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText =  getString(R.styleable.FavoriteDataView_checkBoxText)
                actionButton.text = actionButtontext
                checkBox.text = checkBoxText
            } finally {
                recycle()
            }
        }
    }
}