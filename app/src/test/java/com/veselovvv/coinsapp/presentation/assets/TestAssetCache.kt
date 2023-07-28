package com.veselovvv.coinsapp.presentation.assets

class TestAssetCache : AssetCache {
    override fun save(data: AssetParameters) = Unit
    override fun read() = AssetParameters("bircoin","1", "BTC", "Bitcoin")
}