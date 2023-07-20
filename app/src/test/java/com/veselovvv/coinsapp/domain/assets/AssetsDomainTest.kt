package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import com.veselovvv.coinsapp.presentation.assets.AssetsUi
import com.veselovvv.coinsapp.presentation.assets.BaseAssetDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assets.BaseAssetsDomainToUiMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsDomainTest {
    @Test
    fun test_success() {
        val assets = listOf(
            AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetData(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val resultAssets = listOf(
            AssetDomain(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetDomain(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val assetMapper = BaseAssetDomainToUiMapper()
        val domain = AssetsDomain.Success(assets, BaseAssetDataToDomainMapper())
        val expected = AssetsUi.Success(resultAssets, assetMapper)
        val actual = domain.map(BaseAssetsDomainToUiMapper(TestResourceProvider(), assetMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = AssetsDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = AssetsUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(BaseAssetsDomainToUiMapper(TestResourceProvider(), BaseAssetDomainToUiMapper()))
        assertEquals(expected, actual)

        domain = AssetsDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = AssetsUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(BaseAssetsDomainToUiMapper(TestResourceProvider(), BaseAssetDomainToUiMapper()))
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}