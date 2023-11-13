package com.veselovvv.coinsapp.data.markets

interface ToMarketMapper {
    fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String): MarketData

    class Base : ToMarketMapper {
        override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) =
            MarketData(exchangeId, baseSymbol, quoteSymbol)
    }
}