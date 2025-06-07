package com.example.taichung

import android.database.Observable
import com.example.taichung.data.FlowerResponse
import retrofit2.http.GET

interface FlowerApiService {

    @GET("api/dataset/abe1faae-9d30-4e34-b927-e57ceb616b8a/resource/63e8f311-b70c-47c7-bfe0-5c557c4fa89f/download/flower.json")
    fun getFlowerInfo(): Observable<FlowerResponse>
}
