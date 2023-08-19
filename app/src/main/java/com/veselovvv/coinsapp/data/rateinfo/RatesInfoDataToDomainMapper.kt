package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomain

interface RatesInfoDataToDomainMapper {
    fun map(rateInfo: RateInfoData): RatesInfoDomain
    fun map(e: Exception): RatesInfoDomain
}