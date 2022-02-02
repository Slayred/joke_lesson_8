package com.example.joke_lesson_8.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.domain.interfaces.FavoriteItemClickListener
import com.example.joke_lesson_8.interfaces.CommonCommunication
import com.example.joke_lesson_8.jokeapp.FailedCommonUIModel

class CommonDataRecyclerAdapter<T>(private val listener: FavoriteItemClickListener<T>
, private val communication: CommonCommunication<T>):
RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder<T>>() {

    private val list = ArrayList<CommonUIModel<T>>()
    private var onCreateViewHolderCallsCount = 0
    private var onBindViewHolderCount = 0

    fun show(data: List<CommonUIModel<T>>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

//    fun removeItem(id: T) {
//        val element = list.find {
//            it.matches(id)
//        }
//        val position = list.indexOf(element)
//        list.remove(element)
//        notifyItemChanged(position)
//    }

    fun update() {
        val result = communication.getDiffResult()
        result.dispatchUpdatesTo(this)
    }

    fun update(pair: Pair<Boolean, Int>) {
        if(pair.first) {
            notifyItemInserted(pair.second)
        } else {
            notifyItemRemoved(pair.second)
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when(communication.getList()[position]){
            is FailedCommonUIModel -> 0
            else -> 1
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<T> {
        val emptyList = viewType == 0

        val view = LayoutInflater.from(parent.context).inflate(
            if(emptyList){
            R.layout.no_favorite_item
            } else { R.layout.common_data_item}, parent, false)

        onCreateViewHolderCallsCount++
        Log.d("TAG", "onCreateViewHolderCalls: $onCreateViewHolderCallsCount")
        return if (emptyList) EmptyFavoritesViewHolder(view) else CommonDataViewHolder.Base(view, listener)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder<T>, position: Int) {
        onBindViewHolderCount++
        Log.d("TAG", "onBindVIewHolderCalls: $onBindViewHolderCount")
        holder.bind(communication.getList()[position])
    }

    override fun getItemCount(): Int {
        return  communication.getList().size
    }


    abstract class CommonDataViewHolder<T>(view: View): RecyclerView.ViewHolder(view){
        private val textVIew = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)

        open fun bind(model: CommonUIModel<T>) = model.show(textVIew)

        class Base<T>(view: View, private val listener: FavoriteItemClickListener<T>): CommonDataViewHolder<T>(view){
            private val iconView = itemView.findViewById<CorrectImageButton>(R.id.changeBtn)
            override fun bind(model: CommonUIModel<T>) {
                super.bind(model)
                iconView.setOnClickListener {
                    model.change(listener)
                }
            }
        }
    }


    inner class EmptyFavoritesViewHolder<T>(view: View): CommonDataViewHolder<T>(view)


}