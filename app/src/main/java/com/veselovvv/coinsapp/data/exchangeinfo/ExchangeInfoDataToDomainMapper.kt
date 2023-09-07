package com.veselovvv.coinsapp.data.exchangeinfo

import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomain

interface ExchangeInfoDataToDomainMapper {
    fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ): ExchangeInfoDomain
}