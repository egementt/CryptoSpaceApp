package com.example.cryptospaceapp.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.cryptospaceapp.model.BottomNavItem

object Constant {
    const val BASE_URL_CRYPTO: String = "https://api.coingecko.com/api/v3/"
    const val BASE_URL_NEWS: String = "https://newsapi.org/v2/"
    const val NEWS_API_KEY: String = "d865ca76cf19415381eb76eacd56af6d"
    const val LOG_TAG: String = "crypto_space"

    fun stringFormatter(target: String): String {
        return target.dropLast(3)
    }

    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "News",
            icon = Icons.Default.Menu,
            route = "news"
        ),
        BottomNavItem(
            label = "Favorites",
            icon = Icons.Filled.Favorite,
            route = "favorites"
        )
    )
}