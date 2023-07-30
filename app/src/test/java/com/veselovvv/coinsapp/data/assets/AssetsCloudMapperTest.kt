package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.data.assets.cloud.AssetCloud
import com.veselovvv.coinsapp.data.assets.cloud.AssetsCloudMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = AssetsCloudMapper.Base(ToAssetMapper.Base())

        val assets = listOf(
            AssetCloud(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetCloud(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val expected = listOf(
            AssetData(id = "bitcoin", rank = "1", symbol = "BTC", name = "Bitcoin"),
            AssetData(id = "usd-coin", rank = "5", symbol = "USDC", name = "USD Coin")
        )

        val actual = mapper.map(assets)
        assertEquals(expected, actual)
    }
}