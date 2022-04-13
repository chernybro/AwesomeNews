package com.chernybro.awesomenews.data.remote.dto

import com.chernybro.awesomenews.domain.models.ArticlePreview
import com.chernybro.awesomenews.utils.DateHelper.toIsoString
import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsDTO(
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val articles : List<ArticleDTO>
)

fun NewsDTO.toArticlePreviewList(): List<ArticlePreview> {
    return articles.map { articleDTO ->
        ArticlePreview(
            source = articleDTO.source.name,
            date = articleDTO.publishDate,
            image = articleDTO.imageUrl,
            description = articleDTO.description
        )
    }
}
