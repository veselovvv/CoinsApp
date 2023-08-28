package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomain

interface ExchangeDataToDomainMapper {
    fun map(id: String, name: String, rank: String): ExchangeDomain
}