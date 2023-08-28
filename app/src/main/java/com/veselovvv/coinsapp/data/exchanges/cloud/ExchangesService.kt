package com.veselovvv.coinsapp.data.exchanges.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ExchangesService {
    @GET("exchanges")
    suspend fun fetchExchanges(): ResponseBody
}