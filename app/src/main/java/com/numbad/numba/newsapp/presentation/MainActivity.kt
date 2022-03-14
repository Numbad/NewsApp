package com.numbad.numba.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.numbad.numba.newsapp.presentation.news_details.article.ArticleDetailsScreen
import com.numbad.numba.newsapp.presentation.news_details.video.VideoDetailScreen
import com.numbad.numba.newsapp.presentation.news_list.NewsListScreen
import com.numbad.numba.newsapp.presentation.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        val context = this
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LaunchScreen.route
                    ) {
                        composable(
                            route = Screen.LaunchScreen.route
                        ) {
                            LaunchScreen(navController)
                        }
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            NewsListScreen(navController)
                        }
                        composable(
                            route = Screen.VideoDetailScreen.route + "/{videoId}"
                        ) {
                            VideoDetailScreen(
                                savedStateHandle = navController.currentBackStackEntryAsState().value?.arguments,
                                navHostController = navController
                            )
                        }
                        composable(
                            route = Screen.ArticleDetailScreen.route + "/{articleId}"
                        ) {
                            ArticleDetailsScreen(navHostController = navController, context = context)
                        }
                    }
                }
            }
        }
    }
}