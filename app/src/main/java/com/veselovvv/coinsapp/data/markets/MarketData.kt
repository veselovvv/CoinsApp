package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.markets.MarketDomain

data class MarketData(
    private val exchangeId: String,
    private val baseSymbol: String,
    private val quoteSymbol: String
) : Object<MarketDomain, MarketDataToDomainMapper> {
    override fun map(mapper: MarketDataToDomainMapper) =
        mapper.map(exchangeId, baseSymbol, quoteSymbol)
}
