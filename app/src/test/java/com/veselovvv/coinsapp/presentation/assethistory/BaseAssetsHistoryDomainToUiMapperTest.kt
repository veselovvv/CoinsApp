package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetsHistoryDomainToUiMapperTest {
    private val assetHistoryMapper = BaseAssetHistoryDomainToUiMapper()
    private val mapper = BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), assetHistoryMapper)

    @Test
    fun test_success() {
        val assetsHistory = listOf(
            AssetHistoryDomain(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryDomain(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val expected = AssetsHistoryUi.Success(assetsHistory, assetHistoryMapper)
        val actual = mapper.map(assetsHistory)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsHistoryUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = AssetsHistoryUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}