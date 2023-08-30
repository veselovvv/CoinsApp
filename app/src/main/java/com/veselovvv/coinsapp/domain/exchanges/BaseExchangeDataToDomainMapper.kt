package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangeDataToDomainMapper

class BaseExchangeDataToDomainMapper : ExchangeDataToDomainMapper {
    override fun map(id: String, name: String, rank: String) = ExchangeDomain(id, name, rank)
}