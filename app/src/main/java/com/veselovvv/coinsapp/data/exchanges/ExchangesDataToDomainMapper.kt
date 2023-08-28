package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.domain.exchanges.ExchangesDomain

interface ExchangesDataToDomainMapper {
    fun map(exchanges: List<ExchangeData>): ExchangesDomain
    fun map(e: Exception): ExchangesDomain
}