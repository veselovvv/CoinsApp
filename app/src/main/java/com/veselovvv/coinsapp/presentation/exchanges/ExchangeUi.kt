package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.core.Object

sealed class ExchangeUi : Object<Unit, ExchangeUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(exchangeListener: ExchangesAdapter.ExchangeListener) = Unit

    object Progress : ExchangeUi()

    object NoResults : ExchangeUi()

    data class Base(
        private val id: String,
        private val name: String,
        private val rank: String
    ) : ExchangeUi() {
        override fun map(mapper: BaseMapper) = mapper.map(id, name, rank)
        override fun open(exchangeListener: ExchangesAdapter.ExchangeListener) =
            exchangeListener.showExchange(id, name, rank)
    }

    data class Fail(private val errorMessage: String) : ExchangeUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(id: String, name: String, rank: String)
        fun map(text: String)
    }
}
