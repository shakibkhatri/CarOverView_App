package com.example.caroverview_check24.model

data class Car(
    val id: String,
    val modelId: String,
    val modelName: String,
    val price: String,
    val name: String,
    val brand: String,
    val group: String,
    val series: String,
    val fuelType: String,
    val fuelLevel: Double,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageUrl: String?,
    val isReserved: Boolean
)