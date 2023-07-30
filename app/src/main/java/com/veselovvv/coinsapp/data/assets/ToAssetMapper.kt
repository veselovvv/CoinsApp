package com.veselovvv.coinsapp.data.assets

interface ToAssetMapper {
    fun map(id: String, rank: String, symbol: String, name: String): AssetData

    class Base : ToAssetMapper {
        override fun map(id: String, rank: String, symbol: String, name: String) =
            AssetData(id, rank, symbol, name)
    }
}