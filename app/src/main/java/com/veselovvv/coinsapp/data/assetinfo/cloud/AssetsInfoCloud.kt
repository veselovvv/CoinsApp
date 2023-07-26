package com.veselovvv.coinsapp.data.assetinfo.cloud

import com.google.gson.annotations.SerializedName

data class AssetsInfoCloud(
    @SerializedName("data")
    private val data: AssetInfoCloud
) {
    fun getAssetInfo() = data
}
