package com.example.joke_lesson_8.data

import com.example.joke_lesson_8.core.Mapper
import com.google.gson.annotations.SerializedName

class QuoteServerModel (
    @SerializedName("_id")
    private val id: Int,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String
): Mapper<CommonDataModel>{

    override fun to(): CommonDataModel = CommonDataModel(id,content,author)

}