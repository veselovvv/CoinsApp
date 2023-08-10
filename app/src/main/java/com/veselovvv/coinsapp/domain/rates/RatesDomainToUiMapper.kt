package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType

interface RatesDomainToUiMapper {
    fun map(rates: List<RateDomain>): RatesUi
    fun map(errorType: ErrorType): RatesUi
}