package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.RateDataToDomainMapper
import com.veselovvv.coinsapp.presentation.rates.RatesUi

sealed class RatesDomain : Object<RatesUi, RatesDomainToUiMapper> {
    data class Success(
        private val rates: List<RateData>,
        private val rateMapper: RateDataToDomainMapper
    ) : RatesDomain() {
        override fun map(mapper: RatesDomainToUiMapper) = mapper.map(
            rates.map { rate -> rate.map(rateMapper) }
        )
    }

    data class Fail(private val error: ErrorType) : RatesDomain() {
        override fun map(mapper: RatesDomainToUiMapper) = mapper.map(error)
    }
}
