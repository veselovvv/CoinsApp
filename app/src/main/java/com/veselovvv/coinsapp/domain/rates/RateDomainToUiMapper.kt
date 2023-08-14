package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.presentation.rates.RateUi

interface RateDomainToUiMapper {
    fun map(id: String, symbol: String, rateUsd: String): RateUi
}