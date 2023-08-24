package com.veselovvv.coinsapp.domain.exchanges

class BaseExchangeDataToDomainMapper : ExchangeDataToDomainMapper {
    override fun map(id: String, name: String, rank: String) = ExchangeDomain(id, name, rank)
}