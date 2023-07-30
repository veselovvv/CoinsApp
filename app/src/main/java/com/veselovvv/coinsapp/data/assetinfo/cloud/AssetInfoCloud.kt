package com.veselovvv.coinsapp.data.assetinfo.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import com.veselovvv.coinsapp.data.assetinfo.ToAssetInfoMapper

data class AssetInfoCloud(
    @SerializedName("supply")
    private val supply: String,
    @SerializedName("maxSupply")
    private val maxSupply: String?,
    @SerializedName("marketCapUsd")
    private val marketCapUsd: String,
    @SerializedName("volumeUsd24Hr")
    private val volumeUsd24Hr: String,
    @SerializedName("priceUsd")
    private val priceUsd: String,
    @SerializedName("changePercent24Hr")
    private val changePercent24Hr: String,
    @SerializedName("vwap24Hr")
    private val vwap24Hr: String
) : Object<AssetInfoData, ToAssetInfoMapper> {
    override fun map(mapper: ToAssetInfoMapper) =
        mapper.map(supply, maxSupply ?: NO_DATA, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)

    companion object {
        private const val NO_DATA = "-"
    }
}
