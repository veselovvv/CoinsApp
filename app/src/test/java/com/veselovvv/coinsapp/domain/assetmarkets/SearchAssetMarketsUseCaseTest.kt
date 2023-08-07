package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class SearchAssetMarketsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundAssetsMarkets = listOf(
            AssetMarketsData(exchangeId = "Binance", baseId = "bitcoin", quoteId = "tether")
        )

        val assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        val useCase = SearchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        val expected = AssetsMarketsDomain.Success(foundAssetsMarkets, assetMarketsMapper)
        val actual = useCase.execute(assetId = "bitcoin", query = "Bin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        var useCase = SearchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(UnknownHostException()),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        var expected = AssetsMarketsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(assetId = "bitcoin", query = "Bin")
        assertEquals(expected, actual)

        assetMarketsMapper = BaseAssetMarketsDataToDomainMapper()

        useCase = SearchAssetMarketsUseCase.Base(
            TestAssetMarketsRepository(Exception()),
            BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)
        )

        expected = AssetsMarketsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(assetId = "bitcoin", query = "Bin")
        assertEquals(expected, actual)
    }
}