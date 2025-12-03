package com.example.topplaygroundcompose.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.topplaygroundcompose.R
import com.example.topplaygroundcompose.presentation.article_detail.ArticleDetailScreen
import com.example.topplaygroundcompose.presentation.article_webview.ArticleWebViewScreen
import com.example.topplaygroundcompose.presentation.articles.ArticlesListScreen
import com.example.topplaygroundcompose.presentation.favorites.FavoritesScreen
import com.example.topplaygroundcompose.presentation.navigation.Screen
import java.net.URLDecoder

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val showBottomBar = currentDestination?.route in listOf(
        Screen.ArticlesList.route,
        Screen.Favorites.route
    )
    
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.List, contentDescription = null) },
                        label = { Text(stringResource(R.string.articles_title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.ArticlesList.route } == true,
                        onClick = {
                            navController.navigate(Screen.ArticlesList.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text(stringResource(R.string.favorites_title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.Favorites.route } == true,
                        onClick = {
                            navController.navigate(Screen.Favorites.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.ArticlesList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.ArticlesList.route) {
                ArticlesListScreen(
                    onArticleClick = { articleId ->
                        navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                    }
                )
            }
            
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    onArticleClick = { articleId ->
                        navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                    }
                )
            }
            
            composable(
                route = Screen.ArticleDetail.route,
                arguments = listOf(
                    navArgument("articleId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val articleId = backStackEntry.arguments?.getInt("articleId") ?: 0
                ArticleDetailScreen(
                    articleId = articleId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onOpenFullArticle = { url ->
                        navController.navigate(Screen.ArticleWebView.createRoute(url))
                    }
                )
            }
            
            composable(
                route = Screen.ArticleWebView.route,
                arguments = listOf(
                    navArgument("url") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
                val url = URLDecoder.decode(encodedUrl, "UTF-8")
                ArticleWebViewScreen(
                    url = url,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

