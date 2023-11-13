package com.veselovvv.coinsapp.data.markets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.markets.MarketsDomain

sealed class MarketsData : Object<MarketsDomain, MarketsDataToDomainMapper> {
    data class Success(private val markets: List<MarketData>) : MarketsData() {
        override fun map(mapper: MarketsDataToDomainMapper) = mapper.map(markets)
    }

    data class Fail(private val exception: Exception) : MarketsData() {
        override fun map(mapper: MarketsDataToDomainMapper) = mapper.map(exception)
    }
}
