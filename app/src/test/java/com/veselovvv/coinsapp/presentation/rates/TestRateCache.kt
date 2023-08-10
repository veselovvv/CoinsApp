package com.veselovvv.coinsapp.presentation.rates

class TestRateCache : RateCache {
    override fun save(data: Triple<String, String, String>) = Unit
    override fun read() = Triple("malawian-kwacha", "MWK", "0.0013750106599420")
}