package com.veselovvv.coinsapp.data.assetmarkets.cloud

import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsData
import com.veselovvv.coinsapp.data.assetmarkets.ToAssetMarketsMapper

interface AssetMarketsCloudMapper {
    fun map(assetsMarkets: List<AssetMarketsCloud>): List<AssetMarketsData>

    class Base(private val assetMarketsMapper: ToAssetMarketsMapper) : AssetMarketsCloudMapper {
        override fun map(assetsMarkets: List<AssetMarketsCloud>) = assetsMarkets.map { assetMarkets ->
            assetMarkets.map(assetMarketsMapper)
        }
    }
}