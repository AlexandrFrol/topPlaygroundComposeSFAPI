package com.example.topplaygroundcompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.model.Event
import com.example.topplaygroundcompose.domain.model.Launch

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String?,
    val featured: Boolean,
    val launchesJson: String,
    val eventsJson: String
) {
    fun toArticle(): Article {
        return Article(
            id = id,
            title = title,
            url = url,
            imageUrl = imageUrl,
            newsSite = newsSite,
            summary = summary,
            publishedAt = publishedAt,
            updatedAt = updatedAt,
            featured = featured,
            launches = emptyList(),
            events = emptyList()
        )
    }
    
    companion object {
        fun fromArticle(article: Article, launchesJson: String, eventsJson: String): FavoriteArticleEntity {
            return FavoriteArticleEntity(
                id = article.id,
                title = article.title,
                url = article.url,
                imageUrl = article.imageUrl,
                newsSite = article.newsSite,
                summary = article.summary,
                publishedAt = article.publishedAt,
                updatedAt = article.updatedAt,
                featured = article.featured,
                launchesJson = launchesJson,
                eventsJson = eventsJson
            )
        }
    }
}

