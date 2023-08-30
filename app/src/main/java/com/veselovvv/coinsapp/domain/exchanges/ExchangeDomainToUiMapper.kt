package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.presentation.exchanges.ExchangeUi

interface ExchangeDomainToUiMapper {
    fun map(id: String, name: String, rank: String): ExchangeUi
}