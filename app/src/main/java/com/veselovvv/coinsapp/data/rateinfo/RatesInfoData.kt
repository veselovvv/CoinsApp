package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.rateinfo.RatesInfoDomain

sealed class RatesInfoData : Object<RatesInfoDomain, RatesInfoDataToDomainMapper> {
    data class Success(private val rateInfo: RateInfoData) : RatesInfoData() {
        override fun map(mapper: RatesInfoDataToDomainMapper) = mapper.map(rateInfo)
    }

    data class Fail(private val exception: Exception) : RatesInfoData() {
        override fun map(mapper: RatesInfoDataToDomainMapper) = mapper.map(exception)
    }
}
