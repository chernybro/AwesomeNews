package com.chernybro.awesomenews.data.remote

import com.chernybro.awesomenews.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/top-headlines")
    suspend fun getNewsByCountry(
        @Query("country") country: String
    ): List<NewsDTO>
}