package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.data.rates.RateDataToDomainMapper

class BaseRateDataToDomainMapper : RateDataToDomainMapper {
    override fun map(id: String, symbol: String, rateUsd: String) = RateDomain(id, symbol, rateUsd)
}