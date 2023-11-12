package com.veselovvv.coinsapp.domain.markets

interface MarketDomainToUiMapper {
    fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String): MarketUi
}