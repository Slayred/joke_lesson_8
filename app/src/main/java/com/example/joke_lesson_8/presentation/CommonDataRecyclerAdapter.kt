package com.example.joke_lesson_8.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.data.CommonDataModel

class CommonDataRecyclerAdapter<E>():
RecyclerView.Adapter<CommonDataRecyclerAdapter<E>.CommonDataViewHolder<E>>() {

    private val list = ArrayList<CommonDataModel<E>>()

    fun show(data: List<CommonDataModel<E>>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<E> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        return CommonDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder<E>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }



    inner class CommonDataViewHolder<E>(view: View): RecyclerView.ViewHolder(view){
        private val textVIew = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)

        fun bind(model: CommonDataModel<E>) = model.map(textVIew)
    }
}