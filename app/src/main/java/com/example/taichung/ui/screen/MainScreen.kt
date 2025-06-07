package com.example.taichung.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen(
    flowerList: List<FlowerItem>,
    onWebClick: (String) -> Unit,
    onSwitchLang: () -> Unit
) {
    Column {
        Button(onClick = { onSwitchLang() }) {
            Text(stringResource(id = R.string.btn_switch_lang))
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(flowerList) { item ->
                FlowerItemCard(item, onWebClick)
            }
        }
    }
}
