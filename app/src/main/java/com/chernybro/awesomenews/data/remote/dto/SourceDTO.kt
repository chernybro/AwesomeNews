package com.chernybro.awesomenews.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SourceDTO(
    @SerializedName("id") val id : String?,
    @SerializedName("name") val name : String?
)
