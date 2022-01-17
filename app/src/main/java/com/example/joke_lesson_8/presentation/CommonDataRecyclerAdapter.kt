package com.example.joke_lesson_8.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.data.CommonDataModel

class CommonDataRecyclerAdapter():
RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder>() {

    private val list = ArrayList<CommonUIModel>()
    private var onCreateViewHolderCallsCount = 0
    private var onBindViewHolderCount = 0

    fun show(data: List<CommonUIModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        onCreateViewHolderCallsCount++
        Log.d("TAG", "onCreateViewHolderCalls: $onCreateViewHolderCallsCount")
        return CommonDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        onBindViewHolderCount++
        Log.d("TAG", "onBindVIewHolderCalls: $onBindViewHolderCount")
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }



    inner class CommonDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val textVIew = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)

        fun bind(model: CommonUIModel) = model.map(textVIew)
    }
}