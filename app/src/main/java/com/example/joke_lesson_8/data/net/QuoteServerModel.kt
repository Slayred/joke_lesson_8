package com.example.joke_lesson_8.data.net

import com.example.joke_lesson_8.core.Mapper
import com.example.joke_lesson_8.data.CommonDataModel
import com.google.gson.annotations.SerializedName

class QuoteServerModel (
    @SerializedName("_id")
    private val id: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String
): Mapper<CommonDataModel<String>>{

    override fun to(): CommonDataModel<String> = CommonDataModel(id,content,author)

}