package com.veselovvv.coinsapp.domain.exchangeinfo

interface ExchangeInfoDomainToUiMapper {
    fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ): ExchangeInfoUi
}