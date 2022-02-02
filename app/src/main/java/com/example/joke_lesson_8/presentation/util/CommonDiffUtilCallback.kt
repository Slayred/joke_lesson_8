package com.example.joke_lesson_8.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.joke_lesson_8.presentation.CommonUIModel

class CommonDiffUtilCallback<E>(
    private val oldList: List<CommonUIModel<E>>
    ,private val newList: List<CommonUIModel<E>>
) : DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].same(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  = false
}