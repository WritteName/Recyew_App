package com.example.appmov_prod.Repository

import com.example.appmov_prod.Model.RecyclePoint
import com.example.appmov_prod.Model.TruckRoute
import com.google.android.gms.maps.model.LatLng

class MapsRepository {
    fun getRecyclePoints(): List<RecyclePoint> {
        return listOf(
            RecyclePoint("1", "Parque Garatea 3", -9.118202187634797, -78.5054150864977),
            RecyclePoint("2", "Parque La Paz", -9.1145692928672, -78.51798221349097),
            RecyclePoint("3", "Parque San Juan Bautista", -9.126629046841131, -78.51542000185441),
            RecyclePoint("4", "Parque El Dorado", -9.119231735453022, -78.51791351349098),
            RecyclePoint("5", "Parque Ricardo Moncada Jeico", -9.1145692928672, -78.51798221349097),
            RecyclePoint("6", "Parque Los Jazmines", -9.123165124003746, -78.50813316930925),
            RecyclePoint("7", "Parque El Pescador", -9.115518413253966, -78.5099815),
            RecyclePoint("8", "Parque de la iglesia Sagrada Familia", -9.124733817903826, -78.51832591534536),
            RecyclePoint("9", "Parque de la Exposición y el Arte", -9.13405991661708, -78.52362048967068),
            RecyclePoint("10", "Parque del Amor", -9.128644245203818, -78.51744590475839),
            RecyclePoint("11", "Parque Las Casuarinas Segunda Etapa", -9.129529455732571, -78.51926471349098),
            RecyclePoint("12", "Parque Las Palmas", -9.147590784775751, -78.50786391920002),
            RecyclePoint("13", "Parque Biosaludable Casuarinas", -9.128974711905913, -78.5228273),
            RecyclePoint("14", "Parque UPIS Los Jardines", -9.13582677438961, -78.5139927),
            RecyclePoint("15", "Boulevard Nuevo Amanecer", -9.131499792766501, -78.50971033069075),
            RecyclePoint("16", "Parque Biosaludable Sector 4", -9.130696692084014, -78.5391169962912),
            RecyclePoint("17", "Parque Cívico Urb. El Trapecio", -9.100885393418897, -78.56521829999998),
            RecyclePoint("18", "Parque Americo Suarez", -9.11487965281181, -78.54644381349098),
            RecyclePoint("19", "Parque Principal La Victoria", -9.072626802464262, -78.57634635581829)
        )
    }

    fun getTruckRoutes(): List<TruckRoute> {
        return listOf(
            TruckRoute("Camión 1", listOf(
                LatLng(-12.0464, -77.0428),
                LatLng(-12.0490, -77.0350),
                LatLng(-12.0500, -77.0300)
            ))
        )
    }
}