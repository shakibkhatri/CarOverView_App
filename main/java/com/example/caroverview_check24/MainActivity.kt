package com.example.caroverview_check24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.caroverview_check24.navigation.AppNavHost
import com.example.caroverview_check24.viewModel.CarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val carViewModel: CarViewModel = viewModel()
            MaterialTheme {
                Surface {
                    AppNavHost(navController = rememberNavController(), viewModel = carViewModel)
                }
            }
        }
    }
}
