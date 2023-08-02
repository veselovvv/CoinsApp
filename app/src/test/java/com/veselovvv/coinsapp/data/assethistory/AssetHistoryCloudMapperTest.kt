package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloud
import com.veselovvv.coinsapp.data.assethistory.cloud.AssetHistoryCloudMapper
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AssetHistoryCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = AssetHistoryCloudMapper.Base(ToAssetHistoryMapper.Base())

        val assets = listOf(
            AssetHistoryCloud(priceUsd = "6379.3997635993342453", time = 1530403200000L),
            AssetHistoryCloud(priceUsd = "6466.3135622762295280", time = 1530489600000L)
        )

        val expected = listOf(
            AssetHistoryData(priceUsd = "6379.3997635993342453", time = "1530403200000"),
            AssetHistoryData(priceUsd = "6466.3135622762295280", time = "1530489600000")
        )

        val actual = mapper.map(assets)
        assertEquals(expected, actual)
    }
}