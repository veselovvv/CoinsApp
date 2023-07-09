package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.domain.assets.AssetDomain
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetsDomainToUiMapperTest {
    private val assetMapper = BaseAssetDomainToUiMapper()
    private val mapper = BaseAssetsDomainToUiMapper(TestResourceProvider(), assetMapper)

    @Test
    fun test_success() {
        val assets = listOf(
            AssetDomain(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetDomain(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val expected = AssetsUi.Success(assets, assetMapper)
        val actual = mapper.map(assets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = AssetsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}