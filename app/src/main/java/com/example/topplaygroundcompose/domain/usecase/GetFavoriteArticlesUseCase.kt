package com.example.topplaygroundcompose.domain.usecase

import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getFavoriteArticles()
    }
}

