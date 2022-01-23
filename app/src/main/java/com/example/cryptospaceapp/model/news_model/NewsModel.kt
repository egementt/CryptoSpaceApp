package com.example.cryptospaceapp.model.news_model

import com.example.cryptospaceapp.model.news_model.Article

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)