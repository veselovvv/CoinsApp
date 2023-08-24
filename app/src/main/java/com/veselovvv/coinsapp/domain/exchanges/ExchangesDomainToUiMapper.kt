package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType

interface ExchangesDomainToUiMapper {
    fun map(exchanges: List<ExchangeDomain>): ExchangesUi
    fun map(errorType: ErrorType): ExchangesUi
}