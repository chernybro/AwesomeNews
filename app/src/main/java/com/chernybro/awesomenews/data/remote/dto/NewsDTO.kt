package com.chernybro.awesomenews.data.remote.dto

import com.chernybro.awesomenews.domain.models.ArticlePreview
import com.chernybro.awesomenews.utils.DateHelper.toIsoString
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class NewsDTO(
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val articles : List<ArticleDTO>
)

fun NewsDTO.toArticlePreviewList(): List<ArticlePreview> {
    return articles.map { articleDTO ->
        val actual = OffsetDateTime.parse(articleDTO.publishDate, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        val formatDateTime = actual.format(formatter)
        ArticlePreview(
            source = articleDTO.source.name,
            date = formatDateTime,
            image = articleDTO.imageUrl,
            description = articleDTO.description
        )
    }
}
