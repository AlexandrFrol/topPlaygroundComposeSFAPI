package com.example.topplaygroundcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticlesResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ArticleDto>
)

