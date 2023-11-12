package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.Object

data class MarketDomain(
    private val exchangeId: String,
    private val baseSymbol: String,
    private val quoteSymbol: String
) : Object<MarketUi, MarketDomainToUiMapper> {
    override fun map(mapper: MarketDomainToUiMapper) =
        mapper.map(exchangeId, baseSymbol, quoteSymbol)
}
