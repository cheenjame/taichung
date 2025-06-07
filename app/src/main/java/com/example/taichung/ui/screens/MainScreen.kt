package com.example.taichung.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taichung.R
import com.example.taichung.data.model.FlowerSpot
import com.example.taichung.data.model.MarineRecreationSpot
import com.example.taichung.ui.components.FlowerSpotCard
import com.example.taichung.ui.components.MarineSpotCard
import com.example.taichung.ui.components.WebViewScreen
import com.example.taichung.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onSwitchLanguage: () -> Unit) {
    val marineSpots by viewModel.marineSpots.collectAsState()
    val flowerSpots by viewModel.flowerSpots.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    var selectedTab by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    
    // WebView 導航狀態
    var showWebView by remember { mutableStateOf(false) }
    var webViewUrl by remember { mutableStateOf("") }
    var webViewTitle by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadMarineSpots()
        viewModel.loadFlowerSpots()
    }

    // 顯示 WebView 或主畫面
    if (showWebView) {
        WebViewScreen(
            url = webViewUrl,
            title = webViewTitle,
            onBackClick = { showWebView = false }
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    ) 
                },
                actions = {
                    IconButton(onClick = onSwitchLanguage) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = stringResource(R.string.switch_language)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 搜尋欄
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text(stringResource(R.string.search_spots)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_spots)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )
            
            // 分類標籤
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { 
                        Text(
                            text = "🌸 ${stringResource(R.string.flower_spots)}",
                            style = MaterialTheme.typography.titleMedium
                        ) 
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { 
                        Text(
                            text = "🌊 ${stringResource(R.string.marine_spots)}",
                            style = MaterialTheme.typography.titleMedium
                        ) 
                    }
                )
            }
            
            // 內容區域
            Box(modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (error != null) {
                    ErrorMessage(
                        error = error!!,
                        onRetry = {
                            if (selectedTab == 0) viewModel.loadFlowerSpots()
                            else viewModel.loadMarineSpots()
                        },
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    when (selectedTab) {
                        0 -> FlowerSpotsContent(
                            spots = flowerSpots.filter { 
                                it.safeName.contains(searchQuery, ignoreCase = true) ||
                                it.safeAddress.contains(searchQuery, ignoreCase = true) ||
                                it.safeFlowerType.contains(searchQuery, ignoreCase = true)
                            }
                        )
                        1 -> MarineSpotsContent(
                            spots = marineSpots.filter {
                                it.safeName.contains(searchQuery, ignoreCase = true) ||
                                it.safeAddress.contains(searchQuery, ignoreCase = true) ||
                                it.safeDescription.contains(searchQuery, ignoreCase = true) ||
                                it.safeDistrict.contains(searchQuery, ignoreCase = true) ||
                                it.safeOpeningHours.contains(searchQuery, ignoreCase = true)
                            },
                            onSpotClick = { spot ->
                                // 優先使用官網，其次是地圖連結
                                val url = when {
                                    spot.safeWebsite.isNotEmpty() -> spot.safeWebsite
                                    spot.safeMapLink.isNotEmpty() -> spot.safeMapLink
                                    else -> null
                                }
                                url?.let {
                                    webViewUrl = it
                                    webViewTitle = spot.safeName
                                    showWebView = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun FlowerSpotsContent(spots: List<FlowerSpot>) {
    if (spots.isEmpty()) {
        EmptyState(
            message = stringResource(R.string.no_flower_spots_found),
            icon = "🌸"
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.flower_spots_count, spots.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(spots) { spot -> 
                FlowerSpotCard(
                    spot = spot,
                    onClick = { /* TODO: Navigate to detail */ }
                ) 
            }
        }
    }
}

@Composable
fun MarineSpotsContent(
    spots: List<MarineRecreationSpot>,
    onSpotClick: (MarineRecreationSpot) -> Unit = {}
) {
    if (spots.isEmpty()) {
        EmptyState(
            message = stringResource(R.string.no_marine_spots_found),
            icon = "🌊"
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.marine_spots_count, spots.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(spots) { spot -> 
                MarineSpotCard(
                    spot = spot,
                    onClick = { onSpotClick(spot) }
                ) 
            }
        }
    }
}

@Composable
fun EmptyState(
    message: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
