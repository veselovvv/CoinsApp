package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.rateinfo.RatesInfoUi

interface RatesInfoDomainToUiMapper {
    fun map(rateInfo: RateInfoDomain): RatesInfoUi
    fun map(errorType: ErrorType): RatesInfoUi
}