package com.chernybro.awesomenews.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    @SerializedName("source") val source : SourceDTO,
    @SerializedName("author") val author : String?,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("urlToImage") val imageUrl : String,
    @SerializedName("publishedAt") val publishDate : String,
    @SerializedName("content") val content : String?
)