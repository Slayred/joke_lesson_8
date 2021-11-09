package com.example.joke_lesson_8.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

class CorrectProgressBar: ProgressBar, ShowView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context,attributeSet,defStyleAttr)

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}