package com.veselovvv.coinsapp.domain.assets

class BaseAssetsDataToDomainMapper(
    private val assetMapper: AssetDataToDomainMapper
) : AssetsDataToDomainMapper {
    override fun map(data: List<AssetData>) = AssetsDomain.Success(data, assetMapper)
    override fun map(e: Exception) = AssetsDomain.Fail(getErrorType(e))
}