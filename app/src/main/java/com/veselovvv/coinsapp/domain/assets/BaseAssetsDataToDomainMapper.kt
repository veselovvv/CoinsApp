package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.AssetDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper

class BaseAssetsDataToDomainMapper(
    private val assetMapper: AssetDataToDomainMapper
) : AssetsDataToDomainMapper() {
    override fun map(data: List<AssetData>) = AssetsDomain.Success(data, assetMapper)
    override fun map(e: Exception) = AssetsDomain.Fail(getErrorType(e))
}