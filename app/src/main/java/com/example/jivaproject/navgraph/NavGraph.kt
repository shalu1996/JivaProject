package com.example.jivaproject.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jivaproject.jiva.ui.screen.SellProductScreen
import com.example.jivaproject.jiva.ui.screen.ThankYouScreen
import com.example.jivaproject.jiva.ui.viewmodel.SellerViewModel
import com.example.jivaproject.util.NavOption

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: SellerViewModel) {
    NavHost(navController, startDestination = NavOption.SELL_SCREEN.name) {
        composable(route = NavOption.SELL_SCREEN.name) {
            SellProductScreen(navController,viewModel)
        }
        composable(route = NavOption.THANKYOU_SCREEN.name) {
            ThankYouScreen(navController,viewModel)
        }
    }
}