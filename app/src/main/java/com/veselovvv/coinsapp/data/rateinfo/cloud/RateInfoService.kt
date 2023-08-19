package com.veselovvv.coinsapp.data.rateinfo.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface RateInfoService {
    @GET("rates/{id}")
    suspend fun fetchRateInfo(@Path("id") id: String): ResponseBody
}