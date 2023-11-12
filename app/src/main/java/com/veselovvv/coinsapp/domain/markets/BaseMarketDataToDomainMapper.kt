package com.veselovvv.coinsapp.domain.markets

class BaseMarketDataToDomainMapper : MarketDataToDomainMapper {
    override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) =
        MarketDomain(exchangeId, baseSymbol, quoteSymbol)
}