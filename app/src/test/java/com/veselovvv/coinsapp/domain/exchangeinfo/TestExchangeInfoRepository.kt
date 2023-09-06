package com.veselovvv.coinsapp.domain.exchangeinfo

class TestExchangeInfoRepository(private val exception: Exception? = null) : ExchangeInfoRepository {
    override suspend fun fetchExchangeInfo(id: String) = if (exception == null)
        ExchangesInfoData.Success(
            ExchangeInfoData(
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                tradingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )
        ) else ExchangesInfoData.Fail(exception)
}