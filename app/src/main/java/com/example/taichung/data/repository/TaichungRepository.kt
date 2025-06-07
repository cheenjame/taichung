package com.example.taichung.data.repository

import com.example.taichung.data.api.TaichungApi
import com.example.taichung.data.model.FlowerSpotsResponse
import com.example.taichung.data.model.MarineRecreationResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaichungRepository @Inject constructor(
    private val api: TaichungApi
) {
    fun getMarineRecreationSpots(): Single<MarineRecreationResponse> {
        return api.getMarineRecreationSpots()
    }

    fun getFlowerSpots(): Single<FlowerSpotsResponse> {
        return api.getFlowerSpots()
    }
} 