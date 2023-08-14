package com.veselovvv.coinsapp.presentation.rates

import com.veselovvv.coinsapp.domain.rates.RateDomainToUiMapper

class BaseRateDomainToUiMapper : RateDomainToUiMapper {
    override fun map(id: String, symbol: String, rateUsd: String) = RateUi.Base(id, symbol, rateUsd)
}