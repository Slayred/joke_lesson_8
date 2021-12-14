package com.example.joke_lesson_8.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.joke_lesson_8.R

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
        checkBox = getChildAt(0) as CheckBox
        textView = linear.findViewById(R.id.tvJoke)
        changeButton = linear.findViewById(R.id.changeBtn)
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

    fun show(state: State) = state.show(progressBar,actionButton,textView,changeButton)

    fun linkWith(commonViewModel: CommonViewModel) {

        changeButton.setOnClickListener{
            commonViewModel.changeStatus()
        }
        actionButton.setOnClickListener {
            commonViewModel.getItem()
        }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            commonViewModel.chooseFavorites(isChecked)
        }


    }


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