package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.exchanges.ExchangeData
import com.veselovvv.coinsapp.data.exchanges.ExchangeDataToDomainMapper
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesUi

sealed class ExchangesDomain : Object<ExchangesUi, ExchangesDomainToUiMapper> {
    data class Success(
        private val exchanges: List<ExchangeData>,
        private val exchangeMapper: ExchangeDataToDomainMapper
    ) : ExchangesDomain() {
        override fun map(mapper: ExchangesDomainToUiMapper) = mapper.map(
            exchanges.map { exchange -> exchange.map(exchangeMapper) }
        )
    }

    data class Fail(private val error: ErrorType) : ExchangesDomain() {
        override fun map(mapper: ExchangesDomainToUiMapper) = mapper.map(error)
    }
}
