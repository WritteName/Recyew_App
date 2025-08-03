package com.example.appmov_prod.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmov_prod.Model.RecyclePoint
import com.example.appmov_prod.Model.TruckRoute
import com.example.appmov_prod.Repository.MapsRepository

class MapsViewModel(private val repository: MapsRepository) : ViewModel() {
    private val _recyclePoints = MutableLiveData<List<RecyclePoint>>()
    val recyclePoints: LiveData<List<RecyclePoint>> get() = _recyclePoints

    private val _truckRoutes = MutableLiveData<List<TruckRoute>>()
    val truckRoutes: LiveData<List<TruckRoute>> get() = _truckRoutes

    fun loadMapData() {
        _recyclePoints.value = repository.getRecyclePoints()
        _truckRoutes.value = repository.getTruckRoutes()
    }
}