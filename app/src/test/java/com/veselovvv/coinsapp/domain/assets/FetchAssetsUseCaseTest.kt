package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assets.AssetData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchAssetsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val assets = listOf(
            AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetData(rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val assetMapper = BaseAssetDataToDomainMapper()

        val useCase = FetchAssetsUseCase(
            TestAssetsRepository(),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        val expected = AssetsDomain.Success(assets, assetMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var assetMapper = BaseAssetDataToDomainMapper()

        var useCase = FetchAssetsUseCase(
            TestAssetsRepository(UnknownHostException()),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        var expected = AssetsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        assetMapper = BaseAssetDataToDomainMapper()

        useCase = FetchAssetsUseCase(
            TestAssetsRepository(Exception()),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        expected = AssetsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}