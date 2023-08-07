package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseAssetsMarketsDataToDomainMapperTest {
    private val assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()
    private val mapper = BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)

    @Test
    fun test_success() {
        val assetsMarkets = listOf(
            AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val expected = AssetsMarketsDomain.Success(assetsMarkets, assetMarketsMapper)
        val actual = mapper.map(assetsMarkets)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsMarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = AssetsMarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}