package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.domain.markets.MarketDomainToUiMapper

class BaseMarketDomainToUiMapper : MarketDomainToUiMapper {
    override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) =
        MarketUi.Base(exchangeId, baseSymbol, quoteSymbol)
}