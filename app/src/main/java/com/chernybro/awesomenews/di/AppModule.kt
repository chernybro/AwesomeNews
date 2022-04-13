package com.chernybro.awesomenews.di

import com.chernybro.awesomenews.data.remote.NewsApi
import com.chernybro.awesomenews.utils.Constants
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val url = chain.request().url.newBuilder().addQueryParameter("apiKey", Constants.NEWS_API_KEY).build()
                request.url(url)
                chain.proceed(request.build())
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRatesApi(client: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }
}