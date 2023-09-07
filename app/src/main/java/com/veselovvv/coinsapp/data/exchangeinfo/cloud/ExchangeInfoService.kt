package com.veselovvv.coinsapp.data.exchangeinfo.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeInfoService {
    @GET("exchanges/{id}")
    suspend fun fetchExchangeInfo(@Path("id") id: String): ResponseBody
}