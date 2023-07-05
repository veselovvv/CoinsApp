package com.veselovvv.coinsapp.data.assets.cloud

import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.ToAssetMapper

interface AssetsCloudMapper {
    fun map(assets: List<AssetCloud>): List<AssetData>

    class Base(private val assetMapper: ToAssetMapper) : AssetsCloudMapper {
        override fun map(assets: List<AssetCloud>) = assets.map { asset -> asset.map(assetMapper) }
    }
}