package com.example.topplaygroundcompose.presentation.article_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topplaygroundcompose.domain.model.Article
import com.example.topplaygroundcompose.domain.usecase.GetArticleByIdUseCase
import com.example.topplaygroundcompose.domain.usecase.IsFavoriteUseCase
import com.example.topplaygroundcompose.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ArticleDetailUiState())
    val uiState: StateFlow<ArticleDetailUiState> = _uiState.asStateFlow()
    
    private var currentArticleId: Int? = null
    
    fun loadArticle(articleId: Int) {
        if (currentArticleId == articleId && _uiState.value.article != null) {
            return
        }
        currentArticleId = articleId
        viewModelScope.launch {
            _uiState.value = ArticleDetailUiState(isLoading = true, error = null)
            getArticleByIdUseCase(articleId).fold(
                onSuccess = { article ->
                    val isFavorite = isFavoriteUseCase(articleId)
                    _uiState.value = ArticleDetailUiState(
                        article = article,
                        isFavorite = isFavorite,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { exception ->
                    _uiState.value = ArticleDetailUiState(
                        isLoading = false,
                        error = exception.message
                    )
                }
            )
        }
    }
    
    fun toggleFavorite() {
        val article = _uiState.value.article ?: return
        viewModelScope.launch {
            toggleFavoriteUseCase(article)
            val newIsFavorite = isFavoriteUseCase(article.id)
            _uiState.value = _uiState.value.copy(isFavorite = newIsFavorite)
        }
    }
}

data class ArticleDetailUiState(
    val article: Article? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

