package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchAssetHistoryUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val assetsHistory = listOf(
            AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val assetHistoryMapper = BaseAssetHistoryDataToDomainMapper()

        val useCase = FetchAssetHistoryUseCase.Base(
            TestAssetHistoryRepository(),
            BaseAssetsHistoryDataToDomainMapper(assetHistoryMapper)
        )

        val expected = AssetsHistoryDomain.Success(assetsHistory, assetHistoryMapper)
        val actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var assetHistoryMapper = BaseAssetHistoryDataToDomainMapper()

        var useCase = FetchAssetHistoryUseCase.Base(
            TestAssetHistoryRepository(UnknownHostException()),
            BaseAssetsHistoryDataToDomainMapper(assetHistoryMapper)
        )

        var expected = AssetsHistoryDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)

        assetHistoryMapper = BaseAssetHistoryDataToDomainMapper()

        useCase = FetchAssetHistoryUseCase.Base(
            TestAssetHistoryRepository(Exception()),
            BaseAssetsHistoryDataToDomainMapper(assetHistoryMapper)
        )

        expected = AssetsHistoryDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(assetId = "bitcoin")
        assertEquals(expected, actual)
    }
}