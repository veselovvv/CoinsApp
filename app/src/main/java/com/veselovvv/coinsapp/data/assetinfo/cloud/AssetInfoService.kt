package com.veselovvv.coinsapp.data.assetinfo.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface AssetInfoService {
    @GET("assets/{id}")
    suspend fun fetchAssetInfo(@Path("id") id: String): ResponseBody
}