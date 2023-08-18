package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.Object

data class RateInfoDomain(
    private val currencySymbol: String,
    private val type: String
) : Object<RateInfoUi, RateInfoDomainToUiMapper> {
    override fun map(mapper: RateInfoDomainToUiMapper) = mapper.map(currencySymbol, type)
}
