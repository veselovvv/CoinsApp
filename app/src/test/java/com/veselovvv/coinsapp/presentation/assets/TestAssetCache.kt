package com.veselovvv.coinsapp.presentation.assets

class TestAssetCache : AssetCache {
    override fun saveAssetInfo(info: Triple<String, String, String>) = Unit
    override fun readAssetInfo() = Triple("1", "BTC", "Bitcoin")
}