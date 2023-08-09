package com.veselovvv.coinsapp.data.rates

import org.junit.Assert.assertEquals
import org.junit.Test

class RatesCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = RatesCloudMapper.Base(ToRateMapper.Base())

        val rates = listOf(
            RateCloud(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateCloud(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val expected = listOf(
            RateData(id = "barbadian-dollar", symbol = "BBD", rateUsd = "0.5000000000000000"),
            RateData(id = "malawian-kwacha", symbol = "MWK", rateUsd = "0.0013750106599420")
        )

        val actual = mapper.map(rates)
        assertEquals(expected, actual)
    }
}