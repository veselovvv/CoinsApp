package com.veselovvv.coinsapp.domain.rateinfo

interface RateInfoDomainToUiMapper {
    fun map(currencySymbol: String, type: String): RateInfoUi
}