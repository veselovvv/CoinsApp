package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.exchangeinfo.ExchangesInfoUi

interface ExchangesInfoDomainToUiMapper {
    fun map(exchangeInfo: ExchangeInfoDomain): ExchangesInfoUi
    fun map(errorType: ErrorType): ExchangesInfoUi
}