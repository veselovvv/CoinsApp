package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchAssetInfoUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val assetInfo = AssetInfoData(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )

        val assetInfoDataToDomainMapper = BaseAssetInfoDataToDomainMapper()
        val useCase = FetchAssetInfoUseCase.Base(
            TestAssetInfoRepository(),
            BaseAssetsInfoDataToDomainMapper(assetInfoDataToDomainMapper)
        )
        val expected = AssetsInfoDomain.Success(assetInfo, assetInfoDataToDomainMapper)
        val actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val assetInfoDataToDomainMapper = BaseAssetInfoDataToDomainMapper()
        var useCase = FetchAssetInfoUseCase.Base(
            TestAssetInfoRepository(UnknownHostException()),
            BaseAssetsInfoDataToDomainMapper(assetInfoDataToDomainMapper)
        )
        var expected = AssetsInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)

        useCase = FetchAssetInfoUseCase.Base(
            TestAssetInfoRepository(Exception()),
            BaseAssetsInfoDataToDomainMapper(assetInfoDataToDomainMapper)
        )
        expected = AssetsInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(id = "bitcoin")
        assertEquals(expected, actual)
    }
}