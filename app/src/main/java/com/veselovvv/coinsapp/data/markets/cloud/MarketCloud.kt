package com.veselovvv.coinsapp.data.markets.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.data.markets.ToMarketMapper

data class MarketCloud(
    @SerializedName("exchangeId")
    private val exchangeId: String,
    @SerializedName("baseSymbol")
    private val baseSymbol: String,
    @SerializedName("quoteSymbol")
    private val quoteSymbol: String
) : Object<MarketData, ToMarketMapper> {
    fun exchangeIdStartsWith(query: String) = exchangeId.startsWith(query)

    override fun map(mapper: ToMarketMapper) = mapper.map(exchangeId, baseSymbol, quoteSymbol)
}
