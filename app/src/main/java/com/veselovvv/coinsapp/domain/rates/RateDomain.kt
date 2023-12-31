package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.presentation.rates.RateUi

data class RateDomain(
    private val id: String,
    private val symbol: String,
    private val rateUsd: String
) : Object<RateUi, RateDomainToUiMapper> {
    override fun map(mapper: RateDomainToUiMapper) = mapper.map(id, symbol, rateUsd)
}
