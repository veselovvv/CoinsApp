package com.veselovvv.coinsapp.data.rateinfo

import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloud
import com.veselovvv.coinsapp.data.rateinfo.cloud.RateInfoCloudMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class RateInfoCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = RateInfoCloudMapper.Base(ToRateInfoMapper.Base())
        val expected = RateInfoData(
            currencySymbol = "₿",
            type = "crypto"
        )
        val actual = mapper.map(
            RateInfoCloud(
                currencySymbol = "₿",
                type = "crypto"
            )
        )
        assertEquals(expected, actual)
    }
}