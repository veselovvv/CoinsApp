package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoRepository
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangesInfoData
import java.net.UnknownHostException

class TestExchangeInfoRepository : ExchangeInfoRepository {
    var isSuccess = false

    override suspend fun fetchExchangeInfo(id: String): ExchangesInfoData {
        isSuccess = !isSuccess

        return if (isSuccess)
            ExchangesInfoData.Success(
                ExchangeInfoData(
                    percentTotalVolume = "2.946801735133553120000000000000000000",
                    volumeUsd = "84969370.4499608426167365",
                    tradingPairs = "52",
                    exchangeUrl = "https://kraken.com"
                )
            )
        else ExchangesInfoData.Fail(UnknownHostException())
    }
}