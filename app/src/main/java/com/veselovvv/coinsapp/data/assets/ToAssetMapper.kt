package com.veselovvv.coinsapp.data.assets

interface ToAssetMapper {
    fun map(rank: String, symbol: String, name: String): AssetData

    class Base : ToAssetMapper {
        override fun map(rank: String, symbol: String, name: String) =
            AssetData(rank, symbol, name)
    }
}