package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchanges.ExchangesDomain

sealed class ExchangesData : Object<ExchangesDomain, ExchangesDataToDomainMapper> {
    data class Success(private val exchanges: List<ExchangeData>) : ExchangesData() {
        override fun map(mapper: ExchangesDataToDomainMapper) = mapper.map(exchanges)
    }

    data class Fail(private val exception: Exception) : ExchangesData() {
        override fun map(mapper: ExchangesDataToDomainMapper) = mapper.map(exception)
    }
}
