package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.TestResourceProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AssetsHistoryDomainTest {
    @Test
    fun test_success() {
        val assetsHistory = listOf(
            AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val resultAssetsHistory = listOf(
            AssetHistoryDomain(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryDomain(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val domain = AssetsHistoryDomain.Success(assetsHistory, BaseAssetHistoryDataToDomainMapper())
        val assetHistoryMapper = BaseAssetHistoryDomainToUiMapper()
        val expected = AssetsHistoryUi.Success(resultAssetsHistory, assetHistoryMapper)
        val actual = domain.map(BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), assetHistoryMapper))
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var domain = AssetsHistoryDomain.Fail(ErrorType.NO_CONNECTION)
        var expected = AssetsHistoryUi.Fail(NO_CONNECTION_MESSAGE)
        var actual = domain.map(BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), BaseAssetHistoryDomainToUiMapper()))
        assertEquals(expected, actual)

        domain = AssetsHistoryDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        expected = AssetsHistoryUi.Fail(SERVICE_UNAVAILABLE_MESSAGE)
        actual = domain.map(BaseAssetsHistoryDomainToUiMapper(TestResourceProvider(), BaseAssetHistoryDomainToUiMapper()))
        assertEquals(expected, actual)
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
    }
}