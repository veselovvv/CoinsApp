package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.core.Mapper

interface ToAssetMapper : Mapper {
    fun map(rank: String, symbol: String, name: String): AssetData

    class Base : ToAssetMapper {
        override fun map(rank: String, symbol: String, name: String) =
            AssetData(rank, symbol, name)
    }
}