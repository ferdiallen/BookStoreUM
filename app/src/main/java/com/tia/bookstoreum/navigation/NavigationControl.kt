package com.tia.bookstoreum.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tia.bookstoreum.data.datastore.DataStoreBoardingSettings
import com.tia.bookstoreum.presentation.boarding.BoardingScreen
import com.tia.bookstoreum.presentation.dashboard.DashboardScreen
import com.tia.bookstoreum.presentation.detail.BookDetail
import com.tia.bookstoreum.presentation.read.ReadBookScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationControl(vM: NavigationViewModel = getViewModel()) {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = if (vM.isOpenedAndAccepted) NavAddress.MainScreen.route
        else NavAddress.BoardingScreen.route
    ) {
        composable(NavAddress.ReadBookScreen.route) {
            ReadBookScreen(controller)
        }
        composable(NavAddress.MainScreen.route) {
            DashboardScreen(controller = controller)
        }
        composable(
            "${NavAddress.BookDetailsScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    this.type = NavType.IntType
                }
            )
        ) { out ->
            val args = out.arguments
            args?.let { bundleData ->
                BookDetail(
                    controller = controller,
                    id = bundleData.getInt("id", 0)
                )
            }
        }
        composable(NavAddress.BoardingScreen.route) {
            BoardingScreen(controller = controller)
        }
    }
}