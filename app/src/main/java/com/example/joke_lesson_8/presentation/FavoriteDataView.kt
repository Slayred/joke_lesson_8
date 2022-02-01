package com.example.joke_lesson_8.presentation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.joke_lesson_8.R

class FavoriteDataView: LinearLayout {

    private lateinit var checkBox: CheckBox
    private lateinit var textView: CorrectTextView
    private lateinit var changeButton: CorrectImageButton
    private lateinit var actionButton: CorrectButton
    private lateinit var progressBar: CorrectProgressBar
    //private lateinit var actionBtn: CorrectButton
    //private lateinit var customView: View

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init(attrs)
    }


    private fun init(attrs: AttributeSet){
        orientation = VERTICAL
        /*val k =*/ (context.
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view,this,true)
        val linear = findViewById<ConstraintLayout>(R.id.commonDataLL)
        //val linear = getChildAt(1) as LinearLayout
        Log.d("TAG", "ID IS $linear.id")
        //checkBox = getChildAt(0) as CheckBox
        checkBox = findViewById(R.id.checkBox)
        textView = linear.findViewById(R.id.commonDataTextView)
        changeButton = linear.findViewById(R.id.changeBtn)
        //progressBar = getChildAt(2) as CorrectProgressBar
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = INVISIBLE
        //actionButton = getChildAt(3) as CorrectButton
        actionButton =  findViewById(R.id.btnJoke)
        //actionBtn = k.findViewById(R.id.btnJoke)

        context.theme.obtainStyledAttributes(attrs,R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtontext = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText =  getString(R.styleable.FavoriteDataView_checkBoxText)
                actionButton.text = actionButtontext
                checkBox.text = checkBoxText
                //actionBtn.text = actionButtontext
            } finally {
                recycle()
            }
        }
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

    fun show(state: State) = state.show(progressBar,actionButton,textView,changeButton)

    fun linkWith(commonViewModel: CommonItemViewModel) {

        changeButton.setOnClickListener{
            commonViewModel.changeItemStatus()
        }
        actionButton.setOnClickListener {
            commonViewModel.getItem()
        }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            commonViewModel.chooseFavorites(isChecked)
        }
//        actionBtn.setOnClickListener{
//            commonViewModel.getItem()
//        }


    }


}