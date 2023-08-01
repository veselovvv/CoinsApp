package com.veselovvv.coinsapp.presentation.assethistory

import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsHistoryUiTest {
    @Test
    fun test_success() {
        val assetsHistory = listOf(
            AssetHistoryDomain(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryDomain(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        var ui = AssetsHistoryUi.Success(assetsHistory, BaseAssetHistoryDomainToUiMapper())
        val communication = TestAssetsHistoryCommunication()
        ui.map(communication)

        var expected = listOf<AssetHistoryUi>(
            AssetHistoryUi.Base(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryUi.Base(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        var actual = communication.getAssetHistory()
        assertEquals(expected, actual)

        ui = AssetsHistoryUi.Success(listOf(), BaseAssetHistoryDomainToUiMapper())
        ui.map(communication)

        expected = listOf<AssetHistoryUi>(AssetHistoryUi.NoResults)
        actual = communication.getAssetHistory()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val ui = AssetsHistoryUi.Fail(GENERIC_ERROR_MESSAGE)
        val communication = TestAssetsHistoryCommunication()
        ui.map(communication)

        val expected = listOf<AssetHistoryUi>(AssetHistoryUi.Fail(GENERIC_ERROR_MESSAGE))
        val actual = communication.getAssetHistory()
        assertEquals(expected, actual)
    }

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong. Please try again!"
    }
}