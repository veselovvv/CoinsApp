package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.data.TestException
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloud
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudDataSource
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseAssetInfoRepositoryTest {
    @Test
    fun test_fetch_asset_info_success() = runBlocking {
        val repository = AssetInfoRepository.Base(
            TestAssetInfoCloudDataSource(true),
            AssetInfoCloudMapper.Base(ToAssetInfoMapper.Base())
        )

        val expected = AssetsInfoData.Success(
            AssetInfoData(
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        )
        val actual = repository.fetchAssetInfo(id = "bitcoin")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_asset_info_fail() = runBlocking {
        val repository = AssetInfoRepository.Base(
            TestAssetInfoCloudDataSource(false),
            AssetInfoCloudMapper.Base(ToAssetInfoMapper.Base())
        )
        val expected = AssetsInfoData.Fail(TestException(""))
        val actual = repository.fetchAssetInfo(id = "bitcoin")
        assertEquals(expected, actual)
    }

    class TestAssetInfoCloudDataSource(private val success: Boolean) : AssetInfoCloudDataSource {
        override suspend fun fetchAssetInfo(id: String) = if (success) AssetInfoCloud(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        ) else throw TestException("")
    }
}