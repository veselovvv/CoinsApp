package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rates.RateDomain
import com.veselovvv.coinsapp.domain.rates.RateDomainToUiMapper

sealed class RatesUi : Object<Unit, RatesCommunication> {
    data class Success(
        private val rates: List<RateDomain>,
        private val rateMapper: RateDomainToUiMapper
    ) : RatesUi() {
        override fun map(mapper: RatesCommunication) {
            if (rates.isEmpty())
                mapper.map(listOf(RateUi.NoResults))
            else {
                val ratesUi = rates.map { rate -> rate.map(rateMapper) }
                mapper.map(ratesUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : RatesUi() {
        override fun map(mapper: RatesCommunication) = mapper.map(listOf(RateUi.Fail(errorMessage)))
    }
}
