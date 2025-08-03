package com.example.appmov_prod.Model

import com.google.android.gms.maps.model.LatLng

data class TruckRoute(
    val id: String,
    val routePoints: List<LatLng>
)
