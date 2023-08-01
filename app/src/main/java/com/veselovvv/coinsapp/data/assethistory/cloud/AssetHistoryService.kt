package com.veselovvv.coinsapp.data.assethistory.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface AssetHistoryService {
    @GET("assets/{assetId}/history?interval=d1")
    suspend fun fetchAssetHistory(@Path("assetId") assetId: String): ResponseBody
}