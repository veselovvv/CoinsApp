package com.veselovvv.coinsapp.presentation.exchanges

import com.veselovvv.coinsapp.domain.exchanges.ExchangeDomainToUiMapper

class BaseExchangeDomainToUiMapper : ExchangeDomainToUiMapper {
    override fun map(id: String, name: String, rank: String) = ExchangeUi.Base(id, name, rank)
}