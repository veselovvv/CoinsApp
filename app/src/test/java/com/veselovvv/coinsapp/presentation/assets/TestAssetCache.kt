package com.veselovvv.coinsapp.presentation.assets

class TestAssetCache : AssetCache {
    override fun save(data: Triple<String, String, String>) = Unit
    override fun read() = Triple("1", "BTC", "Bitcoin")
}