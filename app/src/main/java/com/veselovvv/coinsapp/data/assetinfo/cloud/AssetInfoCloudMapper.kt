package com.veselovvv.coinsapp.data.assetinfo.cloud

import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import com.veselovvv.coinsapp.data.assetinfo.ToAssetInfoMapper

interface AssetInfoCloudMapper {
    fun map(assetInfo: AssetInfoCloud): AssetInfoData

    class Base(private val assetInfoMapper: ToAssetInfoMapper) : AssetInfoCloudMapper {
        override fun map(assetInfo: AssetInfoCloud) = assetInfo.map(assetInfoMapper)
    }
}