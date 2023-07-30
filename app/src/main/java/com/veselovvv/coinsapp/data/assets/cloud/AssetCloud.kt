package com.veselovvv.coinsapp.data.assets.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.ToAssetMapper

data class AssetCloud(
    @SerializedName("id")
    private val id: String,
    @SerializedName("rank")
    private val rank: String,
    @SerializedName("symbol")
    private val symbol: String,
    @SerializedName("name")
    private val name: String
) : Object<AssetData, ToAssetMapper> {
    fun nameStartsWith(query: String) = name.startsWith(query)

    override fun map(mapper: ToAssetMapper) = mapper.map(id, rank, symbol, name)
}