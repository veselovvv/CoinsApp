package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseAssetsInfoDataToDomainMapperTest {
    private val assetInfoMapper = BaseAssetInfoDataToDomainMapper()
    private val mapper = BaseAssetsInfoDataToDomainMapper(assetInfoMapper)

    @Test
    fun test_success() {
        val assetInfo = AssetInfoData(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )

        val expected = AssetsInfoDomain.Success(assetInfo, assetInfoMapper)
        val actual = mapper.map(assetInfo)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = AssetsInfoDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = AssetsInfoDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}