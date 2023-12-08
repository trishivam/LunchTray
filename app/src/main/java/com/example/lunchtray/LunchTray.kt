package com.example.lunchtray

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.Screen.AccompanimentScreen
import com.example.lunchtray.Screen.EntreeItemScreen
import com.example.lunchtray.Screen.LunchTrayScreen
import com.example.lunchtray.Screen.OrderCheckOut
import com.example.lunchtray.Screen.SideDishScreen
import com.example.lunchtray.Screen.ViewModel

enum class Routes(){
    LunchTray,
    Entree,
    SideDish,
    Checkout,
    Accompaniment

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTray(
    viewModel: ViewModel = ViewModel()
) {
    val title by remember {
        mutableStateOf("Lunch Tray")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text( text = title ) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) }
    ){it ->
        val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = Routes.LunchTray.name,
            modifier = Modifier.padding(it)
            )
        {
            composable(
                route = Routes.LunchTray.name
            ){
                LunchTrayScreen {
                        navController.navigate( route = Routes.Entree.name )
                }
            }
            // Entree Scree Navigation
            composable(
                route = Routes.Entree.name
            ){
                EntreeItemScreen(
                    onNextButtonClicked = {
                        viewModel.selectedItemList.add(it)
                        navController.navigate(route = Routes.SideDish.name)
                    },
                    onCancelButtonClicked = { navController.navigate(route = Routes.LunchTray.name)
                    }
                )
            }
            // Side Dish Screen Navigation
            composable(
                route  = Routes.SideDish.name
            ){
                SideDishScreen(
                    onNextButtonClicked = {
                        viewModel.selectedItemList.add(it)
                        navController.navigate( route = Routes.Accompaniment.name)
                                          },
                    onCancelButtonClicked = { navController.navigate(route = Routes.LunchTray.name) }
                )
            }

            // Accompaniment Screen
            composable(
                route = Routes.Accompaniment.name
            ){
                AccompanimentScreen(
                    onNextButtonClicked = {
                        viewModel.selectedItemList.add(it)
                        navController.navigate( route = Routes.Checkout.name)
                    },
                    onCancelButtonClicked = { navController.navigate(route = Routes.LunchTray.name) }
                )
            }

            // Order CheckOut Screen
            composable(
                route = Routes.Checkout.name,

            ){
                OrderCheckOut(
                    viewModel.selectedItemList,
                    onSubmitButtonClicked = {},
                    onCancelButtonClicked = { navController.navigate(route = Routes.LunchTray.name) }
                )
            }
        }

    }
}

