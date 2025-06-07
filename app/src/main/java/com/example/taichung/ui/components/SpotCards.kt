package com.example.taichung.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taichung.data.model.FlowerSpot
import com.example.taichung.data.model.MarineRecreationSpot

@Composable
fun MarineSpotCard(spot: MarineRecreationSpot) {
    Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(text = spot.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = spot.address, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = spot.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun FlowerSpotCard(spot: FlowerSpot) {
    Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(text = spot.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = spot.address, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                    text = "Flower Type: ${spot.flowerType}",
                    style = MaterialTheme.typography.bodySmall
            )
            Text(
                    text = "Best Viewing Time: ${spot.bestViewingTime}",
                    style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
