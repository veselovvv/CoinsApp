package com.veselovvv.coinsapp.data.assets.cloud

import com.google.gson.annotations.SerializedName

data class AssetsCloud(
    @SerializedName("data")
    private val data: List<AssetCloud>
) {
    fun getAssetsList() = data
}
