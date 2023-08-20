package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.data.rateinfo.RateInfoDataToDomainMapper

class BaseRateInfoDataToDomainMapper : RateInfoDataToDomainMapper {
    override fun map(currencySymbol: String, type: String) = RateInfoDomain(currencySymbol, type)
}