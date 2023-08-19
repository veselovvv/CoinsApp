package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomain

data class RateInfoData(
    private val currencySymbol: String,
    private val type: String
) : Object<RateInfoDomain, RateInfoDataToDomainMapper> {
    override fun map(mapper: RateInfoDataToDomainMapper) = mapper.map(currencySymbol, type)
}
