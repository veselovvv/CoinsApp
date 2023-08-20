package com.veselovvv.coinsapp.presentation.rateinfo

import com.veselovvv.coinsapp.domain.rateinfo.RateInfoDomainToUiMapper

class BaseRateInfoDomainToUiMapper : RateInfoDomainToUiMapper {
    override fun map(currencySymbol: String, type: String) = RateInfoUi.Base(currencySymbol, type)
}