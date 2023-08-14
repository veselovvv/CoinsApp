package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.core.Object

sealed class RateUi : Object<Unit, RateUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(rateListener: RatesAdapter.RateListener) = Unit

    object Progress : RateUi()

    object NoResults : RateUi()

    data class Base(
        private val id: String,
        private val symbol: String,
        private val rateUsd: String
    ) : RateUi() {
        override fun map(mapper: BaseMapper) = mapper.map(id, symbol, rateUsd)
        override fun open(rateListener: RatesAdapter.RateListener) =
            rateListener.showRate(id, symbol, rateUsd)
    }

    data class Fail(private val errorMessage: String) : RateUi() {
        override fun map(mapper: BaseMapper) = mapper.map(errorMessage)
    }

    interface BaseMapper {
        fun map(id: String, symbol: String, rateUsd: String)
        fun map(text: String)
    }
}
