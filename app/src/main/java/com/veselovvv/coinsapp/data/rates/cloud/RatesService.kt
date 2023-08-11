package com.veselovvv.coinsapp.data.rates.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface RatesService {
    @GET("rates")
    suspend fun fetchRates(): ResponseBody
}