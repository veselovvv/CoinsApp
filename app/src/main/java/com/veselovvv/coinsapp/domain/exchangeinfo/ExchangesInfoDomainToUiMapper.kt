package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType

interface ExchangesInfoDomainToUiMapper {
    fun map(exchangeInfo: ExchangeInfoDomain): ExchangesInfoUi
    fun map(errorType: ErrorType): ExchangesInfoUi
}