package com.example.caroverview_check24.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.caroverview_check24.R
import com.example.caroverview_check24.viewModel.CarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(carId: String, viewModel: CarViewModel) {
    val car = viewModel.getCarById(carId)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(car?.name ?: "Car Details") })
        }
    ) {
        car?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                val painter = rememberImagePainter(
                    data = car.carImageUrl,
                    builder = {
                        placeholder(R.drawable.img)
                        error(R.drawable.img)
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = car.name,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Display Car Details
                Text(text = "Name: ${car.name}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Location: (${car.latitude}, ${car.longitude})", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))

                Row() {
                    Text(text = "Fuel Level: ${car.fuelLevel * 100}%", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Transmission: ${car.transmission}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Price: $${car.price}", style = MaterialTheme.typography.bodyLarge)
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Reserve/Unreserve Button
                if (viewModel.isCarReserved(car)) {
                    Button(onClick = { viewModel.unreserveCar() }) {
                        Text("Unreserve")
                    }
                } else {
                    Button(onClick = { viewModel.reserveCar(car) }) {
                        Text("Reserve")
                    }
                }
            }
        }
    }
}