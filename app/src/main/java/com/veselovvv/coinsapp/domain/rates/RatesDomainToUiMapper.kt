package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.rates.RatesUi

interface RatesDomainToUiMapper {
    fun map(rates: List<RateDomain>): RatesUi
    fun map(errorType: ErrorType): RatesUi
}