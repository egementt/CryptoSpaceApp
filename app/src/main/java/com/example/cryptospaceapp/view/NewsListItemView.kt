package com.example.cryptospaceapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.cryptospaceapp.model.news_model.Article
import java.net.URLEncoder

@ExperimentalCoilApi
@Composable
fun NewsListItemView(item: Article, navHostController: NavHostController) {
    Card(
        Modifier
            .fillMaxWidth()
            .requiredHeight(120.dp)
            .padding(8.dp)
            .clickable(enabled = true) {
                val encodedUrl = URLEncoder.encode(item.url, Charsets.UTF_8.toString())
                navHostController.navigate("webview/$encodedUrl")

            }, elevation = 16.dp
    ) {
        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.3f),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                content = { LoadImage(url = item.urlToImage) })
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item.title, style = TextStyle(fontWeight = FontWeight.SemiBold))
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "by ${item.author}",
                        style = TextStyle(fontWeight = FontWeight.Normal)
                    )
                }
            }
        }
    }
}