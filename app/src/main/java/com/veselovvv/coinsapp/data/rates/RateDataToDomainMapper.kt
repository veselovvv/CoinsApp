package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.domain.rates.RateDomain

interface RateDataToDomainMapper {
    fun map(id: String, symbol: String, rateUsd: String): RateDomain
}