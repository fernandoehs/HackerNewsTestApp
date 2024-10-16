package com.fernandoherrera.hackernewstestapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.fernandoherrera.hackernewstestapp.domain.model.Hit
import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse
import com.fernandoherrera.hackernewstestapp.domain.model.Hit

//import com.fernandoherrera.hackernewstestapp.data.model.remote.HitResponse

@Composable
fun NewsItem(article: Hit, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = article.storyTitle.toString(),
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis
        )
        Row {
            Text(
                text = article.author.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = " - ",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = article.createdAt.toString(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewsItem() {
    val hitMock =Hit(
        objectId = "1",
        storyTitle = "Begin disabling installed extensions still using Manifest V2 in Chrome stable",
        storyUrl = "https://developer.chrome.com/docs/extensions/develop/migrate/mv2-deprecation-timeline",
        author = "eastbound",
        createdAt = "2024-10-12T05:42:22Z"
    )
    NewsItem(hitMock) {}
}
