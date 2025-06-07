package com.example.taichung.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taichung.R
import com.example.taichung.ui.components.FlowerSpotCard
import com.example.taichung.ui.components.MarineSpotCard
import com.example.taichung.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onSwitchLanguage: () -> Unit) {
    val marineSpots by viewModel.marineSpots.collectAsState()
    val flowerSpots by viewModel.flowerSpots.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMarineSpots()
        viewModel.loadFlowerSpots()
    }

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text(stringResource(R.string.app_name)) },
                        actions = {
                            IconButton(onClick = onSwitchLanguage) {
                                Icon(
                                    imageVector = Icons.Default.Language,
                                    contentDescription =
                                        stringResource(R.string.switch_language)
                                )
                            }
                        }
                )
            }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(
                        text = stringResource(R.string.error_loading),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            } else {
                LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                                text = stringResource(R.string.marine_spots),
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(marineSpots) { spot -> MarineSpotCard(spot = spot) }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                                text = stringResource(R.string.flower_spots),
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(flowerSpots) { spot -> FlowerSpotCard(spot = spot) }
                }
            }
        }
    }
}
