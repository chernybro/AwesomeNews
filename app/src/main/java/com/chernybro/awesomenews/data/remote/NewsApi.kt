package com.chernybro.awesomenews.data.remote

import com.chernybro.awesomenews.data.remote.dto.NewsDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getNewsByCountry(
        @Query("country") country: String
    ): Single<NewsDTO>
}