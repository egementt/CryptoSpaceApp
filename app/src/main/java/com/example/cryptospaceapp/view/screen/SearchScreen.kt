package com.example.cryptospaceapp.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.cryptospaceapp.view.CryptoListItemView
import com.example.cryptospaceapp.viewmodel.CryptoViewModel

@ExperimentalCoilApi
@Composable
fun SearchScreen(viewModel: CryptoViewModel = hiltViewModel()) {
    val searchList = viewModel.searchList.observeAsState()
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { textFieldValue ->
                text = textFieldValue
                viewModel.searchCrypto(text.text)

            },
            label = {
                Text(text = "Search Cryptos")
            })
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.fillMaxSize(),content = {
            items(searchList.value!!.size){
                CryptoListItemView(item = searchList.value!![it])
            }
        })
    }
}