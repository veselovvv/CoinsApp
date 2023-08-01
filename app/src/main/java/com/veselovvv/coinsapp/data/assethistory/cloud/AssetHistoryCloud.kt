package com.veselovvv.coinsapp.data.assethistory.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryData
import com.veselovvv.coinsapp.data.assethistory.ToAssetHistoryMapper

data class AssetHistoryCloud(
    @SerializedName("priceUsd")
    private val priceUsd: String,
    @SerializedName("time")
    private val time: Long
) : Object<AssetHistoryData, ToAssetHistoryMapper> {
    override fun map(mapper: ToAssetHistoryMapper) = mapper.map(priceUsd, time)
}
