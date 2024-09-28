package com.example.caroverview_check24.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.caroverview_check24.R
import com.example.caroverview_check24.model.Car
import com.example.caroverview_check24.viewModel.CarViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(navController: NavController, viewModel: CarViewModel) {
    val cars by viewModel.cars.collectAsState(initial = emptyList())
    val cameraPositionState = rememberCameraPositionState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Car Overview") })
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            // Google Map Section
            GoogleMap(
                modifier = Modifier.weight(1f),
                cameraPositionState = cameraPositionState
            ) {
                cars.forEach { car ->
                    val latitude = car.latitude
                    val longitude = car.longitude
                    val isReserved = viewModel.isCarReserved(car)

                    Marker(
                        state = MarkerState(position = LatLng(latitude, longitude)),
                        title = car.name,
                        icon = if (isReserved) {
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                        } else {
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        }
                    )
                }
            }

            // List Section
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cars) { car ->
                    CarListItem(car = car, viewModel = viewModel, onClick = {
                        navController.navigate("detail/${car.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun CarListItem(car: Car, viewModel: CarViewModel, onClick: () -> Unit) {
    val isReserved = viewModel.isCarReserved(car)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (isReserved) Color.Blue.copy(alpha = 0.1f) else Color.Transparent)
    ) {
        AsyncImage(
            model = car.carImageUrl ?: R.drawable.img,
            contentDescription = car.name,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(text = car.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Fuel Level: ${car.fuelLevel * 100}%", style = MaterialTheme.typography.bodyLarge)
            Button(onClick = onClick) {
                Text("Details")
            }
        }
    }
}