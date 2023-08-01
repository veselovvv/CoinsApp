package com.veselovvv.coinsapp.data.assethistory.cloud

import com.google.gson.annotations.SerializedName

data class AssetsHistoryCloud(
    @SerializedName("data")
    private val data: List<AssetHistoryCloud>
) {
    fun getAssetHistoryList() = data
}
