package com.veselovvv.coinsapp.data.assetmarkets

import org.junit.Assert.assertEquals
import org.junit.Test

class AssetMarketsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = AssetMarketsCloudMapper.Base(ToAssetMarketsMapper.Base())

        val assetsMarkets = listOf(
            AssetMarketsCloud(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsCloud(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val expected = listOf(
            AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val actual = mapper.map(assetsMarkets)
        assertEquals(expected, actual)
    }
}