package com.example.topplaygroundcompose.domain.usecase

import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Result<List<Article>> {
        return repository.getArticles()
    }
}

