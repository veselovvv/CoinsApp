package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assets.AssetData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseAssetsDataToDomainMapperTest {
    private val assetMapper = BaseAssetDataToDomainMapper()
    private val mapper = BaseAssetsDataToDomainMapper(assetMapper)

    @Test
    fun test_success() {
        val assets = listOf(
            AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetData(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val expected = AssetsDomain.Success(assets, assetMapper)
        val actual = mapper.map(assets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = AssetsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}