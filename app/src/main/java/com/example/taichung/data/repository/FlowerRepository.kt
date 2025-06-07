package com.example.taichung.data.repository

import com.example.taichung.data.FlowerItem
import com.example.taichung.data.api.FlowerApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FlowerRepository @Inject constructor(
    private val api: FlowerApiService
) {
    fun getFlowerInfo(): Observable<List<FlowerItem>> {
        return api.getFlowerInfo().map { it.data }
    }
}
