package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.markets.MarketsUi

interface MarketsDomainToUiMapper {
    fun map(markets: List<MarketDomain>): MarketsUi
    fun map(errorType: ErrorType): MarketsUi
}