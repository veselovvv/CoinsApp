package com.veselovvv.coinsapp.data.exchanges

import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangeCloud
import com.veselovvv.coinsapp.data.exchanges.cloud.ExchangesCloudMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangesCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = ExchangesCloudMapper.Base(ToExchangeMapper.Base())

        val exchanges = listOf(
            ExchangeCloud(id = "okex", name = "Okex", rank = "1"),
            ExchangeCloud(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val expected = listOf(
            ExchangeData(id = "okex", name = "Okex", rank = "1"),
            ExchangeData(id = "bithumb", name = "Bithumb", rank = "2")
        )

        val actual = mapper.map(exchanges)
        assertEquals(expected, actual)
    }
}