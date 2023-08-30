package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomain
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomainToUiMapper

sealed class ExchangesUi : Object<Unit, ExchangesCommunication> {
    data class Success(
        private val exchanges: List<ExchangeDomain>,
        private val exchangeMapper: ExchangeDomainToUiMapper
    ) : ExchangesUi() {
        override fun map(mapper: ExchangesCommunication) {
            if (exchanges.isEmpty())
                mapper.map(listOf(ExchangeUi.NoResults))
            else {
                val exchangesUi = exchanges.map { exchange -> exchange.map(exchangeMapper) }
                mapper.map(exchangesUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : ExchangesUi() {
        override fun map(mapper: ExchangesCommunication) =
            mapper.map(listOf(ExchangeUi.Fail(errorMessage)))
    }
}
