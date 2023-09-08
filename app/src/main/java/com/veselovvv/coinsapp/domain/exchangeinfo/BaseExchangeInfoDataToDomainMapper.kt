package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoDataToDomainMapper

class BaseExchangeInfoDataToDomainMapper : ExchangeInfoDataToDomainMapper {
    override fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ) = ExchangeInfoDomain(percentTotalVolume, volumeUsd, tradingPairs, exchangeUrl)
}