package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomain

data class ExchangeData(
    private val id: String,
    private val name: String,
    private val rank: String
) : Object<ExchangeDomain, ExchangeDataToDomainMapper> {
    override fun map(mapper: ExchangeDataToDomainMapper) = mapper.map(id, name, rank)
}
