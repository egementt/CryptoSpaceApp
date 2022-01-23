package com.example.cryptospaceapp.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cryptospaceapp.util.Constant

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.DarkGray,
        content = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Constant.BottomNavItems.forEach { bottomNavItem ->
                BottomNavigationItem(
                    selected = currentRoute == bottomNavItem.route,
                    onClick = { navController.navigate(bottomNavItem.route) },
                    icon = {
                        Icon(
                            imageVector = bottomNavItem.icon,
                            contentDescription = bottomNavItem.label,
                            tint = Color.Yellow

                        )
                    }, label = {
                        Text(text = bottomNavItem.label, color = Color.Yellow)
                    }, alwaysShowLabel = false)
            }
        }

    )
}