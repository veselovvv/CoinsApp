package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rates.RateDomain

data class RateData(
    private val id: String,
    private val symbol: String,
    private val rateUsd: String
) : Object<RateDomain, RateDataToDomainMapper> {
    override fun map(mapper: RateDataToDomainMapper) = mapper.map(id, symbol, rateUsd)
}
