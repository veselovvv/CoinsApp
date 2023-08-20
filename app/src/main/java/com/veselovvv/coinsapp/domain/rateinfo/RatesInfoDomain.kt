package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.RateInfoDataToDomainMapper
import com.veselovvv.coinsapp.presentation.rateinfo.RatesInfoUi

sealed class RatesInfoDomain : Object<RatesInfoUi, RatesInfoDomainToUiMapper> {
    data class Success(
        private val rateInfo: RateInfoData,
        private val rateInfoMapper: RateInfoDataToDomainMapper
    ) : RatesInfoDomain() {
        override fun map(mapper: RatesInfoDomainToUiMapper) =
            mapper.map(rateInfo.map(rateInfoMapper))
    }

    data class Fail(private val error: ErrorType) : RatesInfoDomain() {
        override fun map(mapper: RatesInfoDomainToUiMapper) = mapper.map(error)
    }
}
