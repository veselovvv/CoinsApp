package com.veselovvv.coinsapp.data.assetmarkets.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import com.veselovvv.coinsapp.data.assetmarkets.ToAssetMarketsMapper

data class AssetMarketsCloud(
    @SerializedName("exchangeId")
    private val exchangeId: String,
    @SerializedName("baseId")
    private val baseId: String,
    @SerializedName("quoteId")
    private val quoteId: String
) : Object<AssetMarketsData, ToAssetMarketsMapper> {
    fun exchangeIdStartsWith(query: String) = exchangeId.startsWith(query)

    override fun map(mapper: ToAssetMarketsMapper) = mapper.map(exchangeId, baseId, quoteId)
}