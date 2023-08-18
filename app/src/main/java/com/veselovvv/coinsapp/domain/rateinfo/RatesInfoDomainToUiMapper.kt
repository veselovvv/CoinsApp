package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType

interface RatesInfoDomainToUiMapper {
    fun map(rateInfo: RateInfoDomain): RatesInfoUi
    fun map(errorType: ErrorType): RatesInfoUi
}