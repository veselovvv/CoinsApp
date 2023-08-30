package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesUi

interface ExchangesDomainToUiMapper {
    fun map(exchanges: List<ExchangeDomain>): ExchangesUi
    fun map(errorType: ErrorType): ExchangesUi
}