package com.veselovvv.coinsapp.presentation.markets

import com.veselovvv.coinsapp.core.Object

sealed class MarketUi : Object<Unit, MarketUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit

    object Progress : MarketUi()

    object NoResults : MarketUi()

    data class Base(
        private val exchangeId: String,
        private val baseSymbol: String,
        private val quoteSymbol: String
    ) : MarketUi() {
        override fun map(mapper: BaseMapper) = mapper.map(exchangeId, baseSymbol, quoteSymbol)
    }

    data class Fail(private val errorMessage: String) : MarketUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String)
        fun map(text: String)
    }
}