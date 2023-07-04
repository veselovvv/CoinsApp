package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.core.ErrorType

sealed class AssetsDomain : Object<AssetsUi, AssetsDomainToUiMapper> {
    data class Success(
        private val assets: List<AssetData>,
        private val assetMapper: AssetDataToDomainMapper
    ) : AssetsDomain() {
        override fun map(mapper: AssetsDomainToUiMapper) = mapper.map(
            assets.map { asset -> asset.map(assetMapper)}
        )
    }

    data class Fail(private val error: ErrorType) : AssetsDomain() {
        override fun map(mapper: AssetsDomainToUiMapper) = mapper.map(error)
    }
}
