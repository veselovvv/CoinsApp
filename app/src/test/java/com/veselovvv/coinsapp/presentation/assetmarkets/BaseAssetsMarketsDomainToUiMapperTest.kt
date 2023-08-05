package com.veselovvv.coinsapp.presentation.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetsMarketsDomainToUiMapperTest {
    private val assetMarketsMapper = BaseAssetMarketsDomainToUiMapper()
    private val mapper = BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), assetMarketsMapper)

    @Test
    fun test_success() {
        val assetsMarkets = listOf(
            AssetMarketsDomain(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsDomain(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val expected = AssetsMarketsUi.Success(assetsMarkets, assetMarketsMapper)
        val actual = mapper.map(assetsMarkets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsMarketsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = AssetsMarketsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}