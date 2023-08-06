package com.veselovvv.coinsapp.data.assetmarkets.cloud

import com.google.gson.annotations.SerializedName

data class AssetsMarketsCloud(
    @SerializedName("data")
    private val data: List<AssetMarketsCloud>
) {
    fun getAssetMarketsList() = data
}