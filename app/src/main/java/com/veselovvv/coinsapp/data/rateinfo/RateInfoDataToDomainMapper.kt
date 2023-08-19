package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomain

interface RateInfoDataToDomainMapper {
    fun map(currencySymbol: String, type: String): RateInfoDomain
}