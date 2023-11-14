package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.data.markets.MarketDataToDomainMapper

class BaseMarketDataToDomainMapper : MarketDataToDomainMapper {
    override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) =
        MarketDomain(exchangeId, baseSymbol, quoteSymbol)
}