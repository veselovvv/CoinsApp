package com.veselovvv.coinsapp.domain.rates

interface RateDomainToUiMapper {
    fun map(id: String, symbol: String, rateUsd: String): RateUi
}