package com.veselovvv.coinsapp.domain.rateinfo

class BaseRateInfoDataToDomainMapper : RateInfoDataToDomainMapper {
    override fun map(currencySymbol: String, type: String) = RateInfoDomain(currencySymbol, type)
}