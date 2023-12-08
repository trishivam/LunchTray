package com.example.lunchtray.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R

@Composable
fun LunchTrayScreen(
    onButtonClicked: () -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = stringResource(R.string.lunchTray),
            fontWeight = FontWeight.Bold,
//            fontSize = 40.dp,
            modifier = Modifier.padding(top = 40.dp)
        )
        Spacer(modifier = Modifier.padding(150.dp))

        Button(
            onClick = { onButtonClicked() }
        ) {
            Text(
                text = stringResource(R.string.startOrder),
//                modifier = Modifier.padding(10.dp)
            )
        }
    }
}