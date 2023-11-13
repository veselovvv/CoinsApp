package com.veselovvv.coinsapp.data.markets.cloud

import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.data.markets.ToMarketMapper

interface MarketsCloudMapper {
    fun map(markets: List<MarketCloud>): List<MarketData>

    class Base(private val marketMapper: ToMarketMapper) : MarketsCloudMapper {
        override fun map(markets: List<MarketCloud>) = markets.map { market ->
            market.map(marketMapper)
        }
    }
}