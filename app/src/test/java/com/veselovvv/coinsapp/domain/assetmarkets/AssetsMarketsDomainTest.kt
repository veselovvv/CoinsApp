package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.assetmarkets.AssetsMarketsUi
import com.veselovvv.coinsapp.presentation.assetmarkets.BaseAssetMarketsDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assetmarkets.BaseAssetsMarketsDomainToUiMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsMarketsDomainTest {
    @Test
    fun test_success() {
        val assetsMarkets = listOf(
            AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val resultAssetsMarkets = listOf(
            AssetMarketsDomain(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsDomain(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val assetMarketsMapper = BaseAssetMarketsDomainToUiMapper()
        val domain = AssetsMarketsDomain.Success(assetsMarkets, BaseAssetMarketsDataToDomainMapper())
        val expected = AssetsMarketsUi.Success(resultAssetsMarkets, assetMarketsMapper)
        val actual = domain.map(
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), assetMarketsMapper)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = AssetsMarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = AssetsMarketsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), BaseAssetMarketsDomainToUiMapper())
        )
        assertEquals(expected, actual)

        domain = AssetsMarketsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = AssetsMarketsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(
            BaseAssetsMarketsDomainToUiMapper(TestResourceProvider(), BaseAssetMarketsDomainToUiMapper())
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}