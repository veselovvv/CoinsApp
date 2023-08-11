package com.veselovvv.coinsapp.data.rates

import com.veselovvv.coinsapp.domain.rates.RatesDomain

interface RatesDataToDomainMapper {
    fun map(rates: List<RateData>): RatesDomain
    fun map(e: Exception): RatesDomain
}