package com.example.joke_lesson_8.view

import android.content.Context
import android.util.AttributeSet
import java.util.jar.Attributes

class CorrectTextView: androidx.appcompat.widget.AppCompatTextView, ShowText {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(
        context, attrs, defStyleAttr
            )

    override fun show(text: String) {
        setText(text)
    }
}