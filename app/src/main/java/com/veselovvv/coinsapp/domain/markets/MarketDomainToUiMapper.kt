package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.presentation.markets.MarketUi

interface MarketDomainToUiMapper {
    fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String): MarketUi
}