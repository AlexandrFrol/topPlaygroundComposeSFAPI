package com.example.topplaygroundcompose.data.remote

import com.example.topplaygroundcompose.data.remote.dto.ArticleDto
import com.example.topplaygroundcompose.data.remote.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceFlightNewsApi {
    @GET("articles")
    suspend fun getArticles(): ArticlesResponseDto
    
    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") id: Int): ArticleDto
}

