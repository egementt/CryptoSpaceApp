package com.example.cryptospaceapp.service.network

import com.example.cryptospaceapp.model.news_model.NewsModel
import com.example.cryptospaceapp.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NewsService {

    @GET("everything")
    suspend fun listNews(
        @Query("q") q: String = "crypto",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = Constant.NEWS_API_KEY
    ): NewsModel
}