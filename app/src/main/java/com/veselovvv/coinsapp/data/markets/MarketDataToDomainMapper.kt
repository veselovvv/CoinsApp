package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.domain.markets.MarketDomain

interface MarketDataToDomainMapper {
    fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String): MarketDomain
}