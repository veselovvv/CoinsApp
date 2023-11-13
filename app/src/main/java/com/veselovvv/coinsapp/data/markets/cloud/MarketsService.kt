package com.veselovvv.coinsapp.data.markets.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface MarketsService {
    @GET("markets")
    suspend fun fetchMarkets(): ResponseBody
}