package com.example.jivaproject.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jivaproject.jiva.ui.screen.SellProductScreen
import com.example.jivaproject.jiva.ui.screen.ThankYouScreen
import com.example.jivaproject.jiva.ui.viewmodel.SellerViewModel

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: SellerViewModel) {
    NavHost(navController, startDestination = "home") {
        composable(route = "home") {
            SellProductScreen(navController,viewModel)
        }
        composable(route = "thankyou") {
            ThankYouScreen(navController,viewModel)
        }
    }
}