package com.example.lunchtray.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.data.Dish

private const val TAX = 0.84

@Composable
fun OrderCheckOut(
    dishes: List<Dish>,
    onSubmitButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(R.string.orderSummary),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 10.dp, top = 20.dp)
                .align(Alignment.Start)
        )
        dishes.forEach { item ->
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = item.title,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${item.price}",
                    modifier = Modifier
                        .padding(end = 20.dp)
                )
            }
        }
        Divider(modifier = Modifier.padding(8.dp))
        Column (
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(
                text = "Subtotal: ${dishes.sumOf { it.price }}"
            )
            Text(
                text = "TAX: ${TAX}"
            )
            Text(text = "Total: ${dishes.sumOf { it.price } + TAX}")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { onCancelButtonClicked() }
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
//                enabled = selectedValue
                onClick = { onSubmitButtonClicked() }
            ) {
                Text(stringResource(R.string.submit))
            }
        }
    }
}


