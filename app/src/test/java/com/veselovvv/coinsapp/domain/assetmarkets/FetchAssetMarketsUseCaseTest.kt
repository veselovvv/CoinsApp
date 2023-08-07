package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchAssetMarketsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val assetsMarkets = listOf(
            AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether"),
            AssetMarketsData(exchangeId = "Bitfinex", baseId = "bitcoin", quoteId = "united-states-dollar")
        )

        val assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        val useCase = FetchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        val expected = AssetsMarketsDomain.Success(assetsMarkets, assetMarketsMapper)
        val actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        var useCase = FetchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(UnknownHostException()),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        var expected = AssetsMarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)

        assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        useCase = FetchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(Exception()),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        expected = AssetsMarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)
    }
}