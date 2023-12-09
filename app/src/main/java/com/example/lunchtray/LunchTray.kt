package com.example.lunchtray

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.Screen.AccompanimentScreen
import com.example.lunchtray.Screen.EntreeItemScreen
import com.example.lunchtray.Screen.LunchTrayAPPViewModel
import com.example.lunchtray.Screen.LunchTrayScreen
import com.example.lunchtray.Screen.OrderCheckOut
import com.example.lunchtray.Screen.SideDishScreen


enum class Routes(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Entree(title = R.string.chooseEntree),
    SideDish(title = R.string.chooseSidedish),
    Checkout(title = R.string.orderCheckOut),
    Accompaniment(title = R.string.chooseAccompaniment)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuchTrayAppBar(
    currentScreen: Routes,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: LunchTrayAPPViewModel = LunchTrayAPPViewModel(),
    navController: NavHostController = rememberNavController()
) {
    var canPop by remember { mutableStateOf(false) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canPop = controller.previousBackStackEntry != null
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.Start.name
    )
    Scaffold(
        topBar = {
            LuchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canPop,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){it ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.Start.name,
            modifier = Modifier.padding(it)
            )
        {
            composable(
                route = Routes.Start.name
            ){
                LunchTrayScreen {
                        navController.navigate( route = Routes.Entree.name )
                }
            }
            // Entree Screen Navigation
            composable(
                route = Routes.Entree.name
            ){
                EntreeItemScreen(
                    onNextButtonClicked = {
                        viewModel.selectedItemList.add(it)
                        navController.navigate(route = Routes.SideDish.name)
                    },
                    onCancelButtonClicked = { navController.navigate(route = Routes.Start.name)
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
                    onCancelButtonClicked = { navController.navigate(route = Routes.Start.name) }
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
                    onCancelButtonClicked = { navController.navigate(route = Routes.Start.name) }
                )
            }

            // Order CheckOut Screen
            composable(
                route = Routes.Checkout.name,

            ){
                OrderCheckOut(
                    viewModel.selectedItemList,
                    onSubmitButtonClicked = {},
                    onCancelButtonClicked = { navController.navigate(route = Routes.Start.name) }
                )
            }
        }

    }
}

