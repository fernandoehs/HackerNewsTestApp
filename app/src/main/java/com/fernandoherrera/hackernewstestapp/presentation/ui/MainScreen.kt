package com.fernandoherrera.hackernewstestapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fernandoherrera.hackernewstestapp.domain.model.Hit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNewsClick: (url: String) -> Unit,
    onDeletedNewsItem: (Hit) -> Unit
) {
    val viewModel: HackerNewsViewModel = hiltViewModel()
    val news by viewModel.articles.collectAsState()
    val refreshState = rememberPullToRefreshState()
    val state = viewModel.state

    if (news.isEmpty()) {
        Text("Cargando artículos...")
    } else {
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = state.isLoading,
            onRefresh = {
              viewModel.fetchArticles()
            }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(news) { article ->
                    val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
                    LaunchedEffect(swipeToDismissBoxState.currentValue) {
                        if (swipeToDismissBoxState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.deleteArticle(article)
                            swipeToDismissBoxState.snapTo(SwipeToDismissBoxValue.Settled)
                        }
                    }
                    SwipeToDismissBox(
                        state = swipeToDismissBoxState,
                        backgroundContent = {
                            when (swipeToDismissBoxState.dismissDirection) {
                                SwipeToDismissBoxValue.EndToStart -> {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Red)
                                            .padding(horizontal = 20.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            text = "Delete",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.White,
                                        )
                                    }
                                }

                                SwipeToDismissBoxValue.StartToEnd -> {}
                                SwipeToDismissBoxValue.Settled -> {}
                            }
                        },
                        enableDismissFromStartToEnd = false
                    ) {
                        NewsItem(article = article) { article.storyUrl?.let { onNewsClick(it) } }
                    }
                }
            }
        }
    }
}

