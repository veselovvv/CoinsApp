package com.veselovvv.coinsapp.data.exchangeinfo

interface ToExchangeInfoMapper {
    fun map(
        percentTotalVolume: String,
        volumeUsd: String,
        tradingPairs: String,
        exchangeUrl: String
    ): ExchangeInfoData

    class Base : ToExchangeInfoMapper {
        override fun map(
            percentTotalVolume: String,
            volumeUsd: String,
            tradingPairs: String,
            exchangeUrl: String
        ) = ExchangeInfoData(percentTotalVolume, volumeUsd, tradingPairs, exchangeUrl)
    }
}