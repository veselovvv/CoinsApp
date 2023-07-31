package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseAssetsHistoryDataToDomainMapperTest {
    private val assetHistoryMapper = BaseAssetHistoryDataToDomainMapper()
    private val mapper = BaseAssetsHistoryDataToDomainMapper(assetHistoryMapper)

    @Test
    fun test_success() {
        val assetsHistory = listOf(
            AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val expected = AssetsHistoryDomain.Success(assetsHistory, assetHistoryMapper)
        val actual = mapper.map(assetsHistory)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsHistoryDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = AssetsHistoryDomain.Fail(ErrorType.GENERIC_ERROR)
        var actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}