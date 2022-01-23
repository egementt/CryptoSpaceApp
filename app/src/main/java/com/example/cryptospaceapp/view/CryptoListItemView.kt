package com.example.cryptospaceapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.Transformation
import com.example.cryptospaceapp.model.crypto_model.CryptoModelItem
import com.example.cryptospaceapp.util.Constant

@ExperimentalCoilApi
@Composable
fun CryptoListItemView(item: CryptoModelItem) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 4.dp, horizontal = 8.dp), elevation = 16.dp
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            LoadImage(url = item.image)
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )
                Text(
                    text = item.symbol.uppercase(),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                )

            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${item.current_price} $",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )
                Text(
                    text = "%${Constant.stringFormatter(item.price_change_percentage_24h.toString())}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = if (item.price_change_percentage_24h.toString()
                                .contains("-")
                        ) Color.Red else Color.Green
                    )
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun LoadImage(url: String) {
    val painter = rememberImagePainter(data = url.trim(), builder = {
        transformations(
            CircleCropTransformation()
        )
    })
    val state = painter.state
    when (state) {
        is ImagePainter.State.Loading -> CircularProgressIndicator()
        is ImagePainter.State.Error -> Icons.Filled.Refresh
        else -> Image(
            painter = painter,
            contentDescription = "crypto img",
            modifier = Modifier.padding(10.dp)
        )

    }
}