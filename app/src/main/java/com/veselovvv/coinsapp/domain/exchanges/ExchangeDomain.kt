package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.presentation.exchanges.ExchangeUi

data class ExchangeDomain(
    private val id: String,
    private val name: String,
    private val rank: String
) : Object<ExchangeUi, ExchangeDomainToUiMapper> {
    override fun map(mapper: ExchangeDomainToUiMapper) = mapper.map(id, name, rank)
}
