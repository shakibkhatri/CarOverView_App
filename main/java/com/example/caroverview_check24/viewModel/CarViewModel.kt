package com.example.caroverview_check24.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ApiService
import com.example.caroverview_check24.model.Car
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarViewModel : ViewModel() {
    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    // State to manage the reserved car
    private var reservedCar: Car? = null

    init {
        loadCars()
    }

    private fun loadCars() {
        viewModelScope.launch {
            val apiService = ApiService.create()
            try {
                _cars.value = apiService.getCars()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getCarById(id: String): Car? {
        return cars.value.find { it.id == id }
    }

    fun reserveCar(car: Car) {
        if (reservedCar == null) {
            reservedCar = car
            viewModelScope.launch {
                delay(60000)
                unreserveCar()
            }
        }
    }

    fun unreserveCar() {
        reservedCar = null
    }

    fun isCarReserved(car: Car): Boolean {
        return reservedCar == car
    }
}