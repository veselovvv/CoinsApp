package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType

interface MarketsDomainToUiMapper {
    fun map(markets: List<MarketDomain>): MarketsUi
    fun map(errorType: ErrorType): MarketsUi
}