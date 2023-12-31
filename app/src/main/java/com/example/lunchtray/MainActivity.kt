package com.example.lunchtray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lunchtray.Screen.EntreeItemScreen
import com.example.lunchtray.ui.theme.LunchTrayTheme
import com.example.lunchtray.Screen.LunchTrayScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LunchTrayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LunchTrayApp()
                }
            }
        }
    }
}




//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LunchTrayTheme {
//        Greeting("Android")
//    }
//}