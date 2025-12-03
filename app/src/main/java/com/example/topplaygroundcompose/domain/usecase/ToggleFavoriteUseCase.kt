package com.example.topplaygroundcompose.domain.usecase

import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(article: Article) {
        if (repository.isFavorite(article.id)) {
            repository.removeFromFavorites(article.id)
        } else {
            repository.addToFavorites(article)
        }
    }
}

