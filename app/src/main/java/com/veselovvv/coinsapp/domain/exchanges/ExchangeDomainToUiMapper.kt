package com.veselovvv.coinsapp.domain.exchanges

interface ExchangeDomainToUiMapper {
    fun map(id: String, name: String, rank: String): ExchangeUi
}