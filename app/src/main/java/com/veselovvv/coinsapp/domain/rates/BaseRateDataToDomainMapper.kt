package com.veselovvv.coinsapp.domain.rates

class BaseRateDataToDomainMapper : RateDataToDomainMapper {
    override fun map(id: String, symbol: String, rateUsd: String) = RateDomain(id, symbol, rateUsd)
}