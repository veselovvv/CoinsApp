package com.veselovvv.coinsapp.presentation.assetmarkets

class TestAssetMarketsCache : AssetMarketsCache {
    override fun save(data: Triple<String, String, String>) = Unit
    override fun read() = Triple("Binance", "bitcoin", "tether")
}