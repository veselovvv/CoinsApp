package com.veselovvv.coinsapp.data.assets.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface AssetsService {
    @GET("assets")
    suspend fun fetchAssets(): ResponseBody
}