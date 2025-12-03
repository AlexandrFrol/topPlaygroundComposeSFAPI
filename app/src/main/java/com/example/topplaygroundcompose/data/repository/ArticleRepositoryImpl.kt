package com.example.topplaygroundcompose.data.repository

import com.example.topplaygroundcompose.data.local.dao.FavoriteArticleDao
import com.example.topplaygroundcompose.data.local.entity.FavoriteArticleEntity
import com.example.topplaygroundcompose.data.remote.SpaceFlightNewsApi
import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: SpaceFlightNewsApi,
    private val dao: FavoriteArticleDao,
    private val gson: Gson
) : ArticleRepository {
    
    override suspend fun getArticles(): Result<List<Article>> {
        return try {
            val response = api.getArticles()
            val articles = response.results.map { it.toArticle() }
            Result.success(articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getArticleById(id: Int): Result<Article> {
        return try {
            val article = api.getArticleById(id).toArticle()
            Result.success(article)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun getFavoriteArticles(): Flow<List<Article>> {
        return dao.getAllFavorites().map { entities ->
            entities.map { it.toArticle() }
        }
    }
    
    override suspend fun addToFavorites(article: Article) {
        val launchesJson = gson.toJson(article.launches)
        val eventsJson = gson.toJson(article.events)
        val entity = FavoriteArticleEntity.fromArticle(article, launchesJson, eventsJson)
        dao.insertFavorite(entity)
    }
    
    override suspend fun removeFromFavorites(articleId: Int) {
        dao.deleteFavorite(articleId)
    }
    
    override suspend fun isFavorite(articleId: Int): Boolean {
        return dao.isFavorite(articleId)
    }
}

