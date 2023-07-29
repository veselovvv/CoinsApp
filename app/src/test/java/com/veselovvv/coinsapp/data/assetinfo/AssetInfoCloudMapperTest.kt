package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloud
import com.veselovvv.coinsapp.data.assetinfo.cloud.AssetInfoCloudMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetInfoCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = AssetInfoCloudMapper.Base(ToAssetInfoMapper.Base())
        val expected = AssetInfoData(
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119179791817.6740161068269075",
            volumeUsd24Hr = "2928356777.6066665425687196",
            priceUsd = "6931.5058555666618359",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
        val actual = mapper.map(
            AssetInfoCloud(
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        )
        assertEquals(expected, actual)
    }
}