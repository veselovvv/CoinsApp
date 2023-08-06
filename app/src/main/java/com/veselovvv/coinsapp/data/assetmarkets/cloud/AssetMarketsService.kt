package com.veselovvv.coinsapp.data.assetmarkets.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface AssetMarketsService {
    @GET("assets/{assetId}/markets")
    suspend fun fetchAssetMarkets(@Path("assetId") assetId: String): ResponseBody
}