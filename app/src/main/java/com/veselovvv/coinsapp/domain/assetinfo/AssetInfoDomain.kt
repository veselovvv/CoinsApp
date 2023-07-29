package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.presentation.assetinfo.AssetInfoUi

data class AssetInfoDomain(
    private val supply: String,
    private val maxSupply: String,
    private val marketCapUsd: String,
    private val volumeUsd24Hr: String,
    private val priceUsd: String,
    private val changePercent24Hr: String,
    private val vwap24Hr: String
) : Object<AssetInfoUi, AssetInfoDomainToUiMapper> {
    override fun map(mapper: AssetInfoDomainToUiMapper) =
        mapper.map(supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr)
}
