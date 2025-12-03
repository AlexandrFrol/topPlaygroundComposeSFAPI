package com.example.topplaygroundcompose.domain.repository

import com.example.topplaygroundcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(): Result<List<Article>>
    suspend fun getArticleById(id: Int): Result<Article>
    fun getFavoriteArticles(): Flow<List<Article>>
    suspend fun addToFavorites(article: Article)
    suspend fun removeFromFavorites(articleId: Int)
    suspend fun isFavorite(articleId: Int): Boolean
}

