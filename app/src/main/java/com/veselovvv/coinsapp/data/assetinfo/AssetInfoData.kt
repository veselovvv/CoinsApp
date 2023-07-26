package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomain

data class AssetInfoData(
    private val supply: String,
    private val maxSupply: String,
    private val marketCapUsd: String,
    private val volumeUsd24Hr: String,
    private val priceUsd: String,
    private val changePercent24Hr: String,
    private val vwap24Hr: String
) : Object<AssetInfoDomain, AssetInfoDataToDomainMapper> {
    override fun map(mapper: AssetInfoDataToDomainMapper) =
        mapper.map(supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)
}
