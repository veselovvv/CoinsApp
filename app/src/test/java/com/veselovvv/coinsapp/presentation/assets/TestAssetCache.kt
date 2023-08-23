package com.veselovvv.coinsapp.presentation.assets

class TestAssetCache : AssetCache {
    override fun save(data: AssetParameters) = Unit
    override fun read() = AssetParameters("bitcoin","1", "BTC", "Bitcoin")
}