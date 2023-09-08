package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.presentation.exchangeinfo.ExchangeInfoUi

interface ExchangeInfoDomainToUiMapper {
    fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ): ExchangeInfoUi
}