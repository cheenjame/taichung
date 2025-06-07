package com.example.taichung.data.api

import com.example.taichung.data.model.FlowerSpot
import com.example.taichung.data.model.FlowerSpotsResponse
import com.example.taichung.data.model.MarineRecreationResponse
import com.example.taichung.data.model.MarineRecreationSpot
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TaichungApi {
    // 賞花資訊API - 直接回傳陣列
    @GET("swagger/OpenData/f116d1db-56f7-4984-bad8-c82e383765c0")
    fun getFlowerSpots(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Single<List<FlowerSpot>>

    // 海域遊憩活動API - 直接回傳陣列
    @GET("swagger/OpenData/38476e5e-9288-4b83-bb33-384b1b36c570") 
    fun getMarineRecreationSpots(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Single<List<MarineRecreationSpot>>
}
