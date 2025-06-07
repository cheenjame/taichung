package com.example.taichung.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taichung.data.model.FlowerSpot
import com.example.taichung.data.model.MarineRecreationSpot
import com.example.taichung.data.repository.TaichungRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TaichungRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _marineSpots = MutableStateFlow<List<MarineRecreationSpot>>(emptyList())
    val marineSpots: StateFlow<List<MarineRecreationSpot>> = _marineSpots.asStateFlow()

    private val _flowerSpots = MutableStateFlow<List<FlowerSpot>>(emptyList())
    val flowerSpots: StateFlow<List<FlowerSpot>> = _flowerSpots.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadMarineSpots() {
        _isLoading.value = true
        disposables.add(
            repository.getMarineRecreationSpots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ spots ->
                    android.util.Log.d("MainViewModel", "Marine API response: $spots")
                    android.util.Log.d("MainViewModel", "Marine spots count: ${spots.size}")
                    spots.forEach { spot ->
                        android.util.Log.d("MainViewModel", "Marine spot: ${spot.safeName}, ${spot.safeAddress}")
                    }
                    _marineSpots.value = spots
                    _isLoading.value = false
                }, { error ->
                    android.util.Log.e("MainViewModel", "Error loading marine spots", error)
                    _error.value = "載入海域遊憩資料失敗: ${error.message}"
                    _isLoading.value = false
                })
        )
    }

    fun loadFlowerSpots() {
        _isLoading.value = true
        disposables.add(
            repository.getFlowerSpots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ spots ->
                    android.util.Log.d("MainViewModel", "Flower API response: $spots")
                    android.util.Log.d("MainViewModel", "Flower spots count: ${spots.size}")
                    spots.forEach { spot ->
                        android.util.Log.d("MainViewModel", "Flower spot: ${spot.safeName}, ${spot.safeAddress}")
                    }
                    _flowerSpots.value = spots
                    _isLoading.value = false
                }, { error ->
                    android.util.Log.e("MainViewModel", "Error loading flower spots", error)
                    _error.value = "載入賞花資料失敗: ${error.message}"
                    _isLoading.value = false
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
