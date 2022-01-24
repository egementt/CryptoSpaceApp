package com.example.cryptospaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.cryptospaceapp.navigation.BottomNavigationBar
import com.example.cryptospaceapp.ui.theme.CryptoSpaceAppTheme
import com.example.cryptospaceapp.view.screen.HomeScreen
import com.example.cryptospaceapp.view.screen.NewsScreen
import com.example.cryptospaceapp.view.screen.SearchScreen
import com.example.cryptospaceapp.view.screen.WebViewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CryptoSpaceAppTheme {
                Surface(color = Color.White) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { paddingValues ->
                            NavHostContainer(navController = navController, padding = paddingValues)
                        },
                        topBar = {
                            TopAppBar(
                                backgroundColor = Color.DarkGray,
                                contentPadding = PaddingValues(horizontal = 8.dp),
                            ) {
                                Row(
                                    Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Crypto Space",
                                        style = TextStyle(
                                            color = Color.Yellow,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp
                                        )
                                    )

                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable("home") {
                HomeScreen()
            }
            composable("news") {
                NewsScreen(navHostController = navController)
            }
            composable("search") {
                SearchScreen()
            }
            composable(
                "webview/{webpage}",
                arguments = listOf(navArgument("webpage") { type = NavType.StringType }),
            ) {
                navBackStackEntry ->
                val url = navBackStackEntry.arguments?.getString("webpage")
                if (url != null) {
                        WebViewScreen(url = url)
                }

            }
        }
    )
}

/*
@Composable
fun TestScreen(viewModel: CryptoViewModel = hiltViewModel()) {

}*/
