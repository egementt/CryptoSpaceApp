package com.example.cryptospaceapp.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.cryptospaceapp.model.crypto_model.CryptoModelItem
import com.example.cryptospaceapp.util.Response
import com.example.cryptospaceapp.view.CryptoListItemView
import com.example.cryptospaceapp.viewmodel.CryptoViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen(viewModel: CryptoViewModel = hiltViewModel()) {
    val response = viewModel.response.observeAsState()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (response.value) {
            is Response.Loading -> CircularProgressIndicator()
            is Response.Success -> CryptoListView(cryptoList = response.value!!.data!!)
            is Response.Error -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = "error_icon")
                    Text(
                        text = response.value!!.msg!!,
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun CryptoListView(
    cryptoList: ArrayList<CryptoModelItem>,
    viewModel: CryptoViewModel = hiltViewModel()
) {
    LazyColumn(modifier = Modifier.fillMaxSize(), content = {
        items(cryptoList.size) { item ->
            CryptoListItemView(item = cryptoList[item])
            if (item == cryptoList.lastIndex) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { viewModel.getAllCryptos(viewModel.currentPage - 1) },
                        enabled = viewModel.currentPage != 1
                    ) {
                        Box(
                            Modifier
                                .size(50.dp)
                                .clip(shape = MaterialTheme.shapes.medium)
                                .background(Color.DarkGray), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "previous page",
                                tint = Color.Yellow
                            )
                        }
                    }
                    Text(
                        text = "Page ${viewModel.currentPage}",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, color = Color.Yellow)
                    )
                    IconButton(onClick = { viewModel.getAllCryptos(viewModel.currentPage + 1) }) {
                        Box(
                            Modifier
                                .size(50.dp)
                                .clip(shape = MaterialTheme.shapes.medium)
                                .background(Color.DarkGray), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "next page",
                                tint = Color.Yellow
                            )
                        }
                    }

                }
            }
        }

    })
}

