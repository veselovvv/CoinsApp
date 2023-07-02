package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class SearchAssetsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val foundAssets = listOf(AssetData(rank = "1", symbol = "BTC", name = "Bitcoin"))
        val assetMapper = BaseAssetDataToDomainMapper()

        val useCase = SearchAssetsUseCase(
            TestAssetsRepository(),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        val expected = AssetsDomain.Success(foundAssets, assetMapper)
        val actual = useCase.execute("Bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var assetMapper = BaseAssetDataToDomainMapper()

        var useCase = SearchAssetsUseCase(
            TestAssetsRepository(UnknownHostException()),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        var expected = AssetsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute("")
        assertEquals(expected, actual)

        assetMapper = BaseAssetDataToDomainMapper()

        useCase = SearchAssetsUseCase(
            TestAssetsRepository(Exception()),
            BaseAssetsDataToDomainMapper(assetMapper)
        )

        expected = AssetsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute("")
        assertEquals(expected, actual)
    }
}