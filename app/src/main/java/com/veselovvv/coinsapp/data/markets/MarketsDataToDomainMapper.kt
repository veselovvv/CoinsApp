package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.domain.markets.MarketsDomain

interface MarketsDataToDomainMapper {
    fun map(markets: List<MarketData>): MarketsDomain
    fun map(e: Exception): MarketsDomain
}