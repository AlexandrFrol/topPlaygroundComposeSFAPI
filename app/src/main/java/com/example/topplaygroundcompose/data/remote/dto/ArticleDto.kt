package com.example.topplaygroundcompose.data.remote.dto

import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.model.Event
import com.example.topplaygroundcompose.domain.model.Launch
import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val id: Int,
    val title: String,
    val url: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("news_site")
    val newsSite: String,
    val summary: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val featured: Boolean,
    val launches: List<LaunchDto>,
    val events: List<EventDto>
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
            launches = launches.map { it.toLaunch() },
            events = events.map { it.toEvent() }
        )
    }
}

data class LaunchDto(
    val id: String,
    val provider: String
) {
    fun toLaunch(): Launch {
        return Launch(
            id = id,
            provider = provider
        )
    }
}

data class EventDto(
    val id: Int,
    val provider: String
) {
    fun toEvent(): Event {
        return Event(
            id = id,
            provider = provider
        )
    }
}

