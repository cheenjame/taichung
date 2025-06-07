package com.example.taichung.data.repository

import com.example.taichung.data.api.TaichungApi
import com.example.taichung.data.model.FlowerSpot
import com.example.taichung.data.model.FlowerSpotsResponse
import com.example.taichung.data.model.MarineRecreationResponse
import com.example.taichung.data.model.MarineRecreationSpot
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaichungRepository @Inject constructor(
    private val api: TaichungApi
) {
    fun getMarineRecreationSpots(): Single<List<MarineRecreationSpot>> {
        return api.getMarineRecreationSpots()
    }

    fun getFlowerSpots(): Single<List<FlowerSpot>> {
        return api.getFlowerSpots()
    }
} 