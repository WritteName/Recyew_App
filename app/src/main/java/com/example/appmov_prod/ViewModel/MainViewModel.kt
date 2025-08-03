package com.example.appmov_prod.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmov_prod.Model.OnboardingItem
import com.example.appmov_prod.R

class MainViewModel : ViewModel() {
    private val _onboardingItems = MutableLiveData<List<OnboardingItem>>()
    val onboardingItems: LiveData<List<OnboardingItem>> = _onboardingItems

    init {
        loadItems()
    }

    private fun loadItems() {
        _onboardingItems.value = listOf(
            OnboardingItem(
                imageRes = R.drawable.slider,
                title = "Recycle from Home",
                description = "Find the nearest recycling drop-off points with real-time updates."
            ),
            OnboardingItem(
                imageRes = R.drawable.slider2,
                title = "Nearby Recycling Stations",
                description = "Find the nearest recycling drop-off points with real-time updates."
            )
        )
    }
}