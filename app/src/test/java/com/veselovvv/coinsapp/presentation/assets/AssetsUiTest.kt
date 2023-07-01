package com.veselovvv.coinsapp.presentation.assets

import org.junit.Test

class AssetsUiTest {
    @Test
    fun test_success() {
        val assets = listOf(
            AssetDomain(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetDomain(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        var ui = AssetsUi.Success(assets, BaseAssetDomainToUiMapper())
        val communication = TestAssetsCommunication()
        ui.map(communication)

        var expected = listOf<AssetUi>(
            AssetUi.Base(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetUi.Base(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        var actual = communication.getAssets()
        assertEquals(expected, actual)

        ui = AssetsUi.Success(listOf(), BaseAssetDomainToUiMapper())
        ui.map(communication)

        expected = listOf<AssetUi>(AssetUi.NoResults)
        actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = AssetsUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestAssetsCommunication()
        ui.map(communication)

        val expected = listOf<AssetUi>(AssetUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssets()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}