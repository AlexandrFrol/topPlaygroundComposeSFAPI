package com.example.topplaygroundcompose.domain.usecase

import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(id: Int): Result<Article> {
        return repository.getArticleById(id)
    }
}

