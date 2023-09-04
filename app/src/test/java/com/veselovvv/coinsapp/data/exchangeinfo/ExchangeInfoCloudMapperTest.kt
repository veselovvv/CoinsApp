package com.veselovvv.coinsapp.data.exchangeinfo

import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangeInfoCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = ExchangeInfoCloudMapper.Base(ToExchangeInfoMapper.Base())
        val expected = ExchangeInfoData(
            percentTotalVolume = "2.946801735133553120000000000000000000",
            volumeUsd = "84969370.4499608426167365",
            tradingPairs = "52",
            exchangeUrl = "https://kraken.com"
        )
        val actual = mapper.map(
            ExchangeInfoCloud(
                percentTotalVolume = "2.946801735133553120000000000000000000",
                volumeUsd = "84969370.4499608426167365",
                tradingPairs = "52",
                exchangeUrl = "https://kraken.com"
            )
        )
        assertEquals(expected, actual)
    }
}