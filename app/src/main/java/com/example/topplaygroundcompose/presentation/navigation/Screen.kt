package com.example.topplaygroundcompose.presentation.navigation

sealed class Screen(val route: String) {
    object ArticlesList : Screen("articles_list")
    object Favorites : Screen("favorites")
    object ArticleDetail : Screen("article_detail/{articleId}") {
        fun createRoute(articleId: Int) = "article_detail/$articleId"
    }
    object ArticleWebView : Screen("article_webview/{url}") {
        fun createRoute(url: String) = "article_webview/${java.net.URLEncoder.encode(url, "UTF-8")}"
    }
}

