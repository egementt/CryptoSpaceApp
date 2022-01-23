package com.example.cryptospaceapp.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.cryptospaceapp.model.news_model.Article
import com.example.cryptospaceapp.util.Response
import com.example.cryptospaceapp.view.NewsListItemView
import com.example.cryptospaceapp.viewmodel.NewsViewModel

@ExperimentalCoilApi
@Composable
fun NewsScreen(newsViewModel: NewsViewModel = hiltViewModel(), navHostController: NavHostController) {
    val newsList = newsViewModel.response.observeAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (newsList.value) {
            is Response.Loading -> CircularProgressIndicator()
            is Response.Success -> {
                NewsListView(newsList = newsList.value!!.data!!.articles, navHostController)
            }
            is Response.Error -> {
                Icons.Default.Warning
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun NewsListView(newsList: List<Article>, navHostController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(newsList.size) {
            NewsListItemView(item = newsList[it], navHostController)
        }
    }
}

