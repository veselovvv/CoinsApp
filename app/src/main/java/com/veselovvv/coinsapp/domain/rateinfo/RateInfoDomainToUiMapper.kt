package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.presentation.rateinfo.RateInfoUi

interface RateInfoDomainToUiMapper {
    fun map(currencySymbol: String, type: String): RateInfoUi
}