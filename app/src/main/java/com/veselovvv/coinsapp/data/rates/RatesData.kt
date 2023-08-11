package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rates.RatesDomain

sealed class RatesData : Object<RatesDomain, RatesDataToDomainMapper> {
    data class Success(private val rates: List<RateData>) : RatesData() {
        override fun map(mapper: RatesDataToDomainMapper) = mapper.map(rates)
    }

    data class Fail(private val exception: Exception) : RatesData() {
        override fun map(mapper: RatesDataToDomainMapper) = mapper.map(exception)
    }
}
