package com.example.lunchtray.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.data.Dish

@Composable
fun AccompanimentScreen(
    onNextButtonClicked: (Dish) -> Unit,
    onCancelButtonClicked: () -> Unit,
    viewModel: ViewModel = ViewModel()
) {
    var selectedValue by remember {
        mutableStateOf(
            Dish(
                title = "",
                description = "",
                price = 0.00
            )
        )
    }
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val getAccompanimentList = viewModel.accompanimentMenuItemsList
        Text(
            text = "Choose Accompaniment",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )
        getAccompanimentList.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = { selectedValue = item }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = { selectedValue = item }
                )
                Column {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding()
                    )
                    Text(
                        text = item.description,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = item.price.toString(),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Divider(modifier = Modifier.padding(8.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
//                enabled = selectedValue.toString().isEmpty(),
                onClick = { onNextButtonClicked( selectedValue ) }
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}