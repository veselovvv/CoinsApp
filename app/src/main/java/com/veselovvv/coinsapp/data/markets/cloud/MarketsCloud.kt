package com.veselovvv.coinsapp.data.markets.cloud

import com.google.gson.annotations.SerializedName

data class MarketsCloud(
    @SerializedName("data")
    private val data: List<MarketCloud>
) {
    fun getMarketsList() = data
}
