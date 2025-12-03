package com.example.topplaygroundcompose.domain.usecase

import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: Int): Boolean {
        return repository.isFavorite(articleId)
    }
}

