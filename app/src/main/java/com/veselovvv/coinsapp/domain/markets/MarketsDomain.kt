package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.markets.MarketData
import com.veselovvv.coinsapp.data.markets.MarketDataToDomainMapper
import com.veselovvv.coinsapp.presentation.markets.MarketsUi

sealed class MarketsDomain : Object<MarketsUi, MarketsDomainToUiMapper> {
    data class Success(
        private val markets: List<MarketData>,
        private val marketMapper: MarketDataToDomainMapper
    ) : MarketsDomain() {
        override fun map(mapper: MarketsDomainToUiMapper) = mapper.map(
            markets.map { market -> market.map(marketMapper) }
        )
    }

    data class Fail(private val error: ErrorType) : MarketsDomain() {
        override fun map(mapper: MarketsDomainToUiMapper) = mapper.map(error)
    }
}