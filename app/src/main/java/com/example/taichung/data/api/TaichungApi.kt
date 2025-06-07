package com.example.taichung.data.api

import com.example.taichung.data.model.FlowerSpotsResponse
import com.example.taichung.data.model.MarineRecreationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface TaichungApi {
    @GET("abe1faae-9d304e34-b927-e57ceb616b8a")
    fun getMarineRecreationSpots(): Single<MarineRecreationResponse>

    @GET("49cf443c-aa5848d883c0-dfd6a3dc8f52") fun getFlowerSpots(): Single<FlowerSpotsResponse>
}
