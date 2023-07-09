package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assets.AssetDomain
import com.veselovvv.coinsapp.domain.assets.AssetDomainToUiMapper

sealed class AssetsUi : Object<Unit, AssetsCommunication> {
    data class Success(
        private val assets: List<AssetDomain>,
        private val assetMapper: AssetDomainToUiMapper
    ) : AssetsUi() {
        override fun map(mapper: AssetsCommunication) {
            if (assets.isEmpty())
                mapper.map(listOf(AssetUi.NoResults))
            else {
                val assetsUi = assets.map { asset -> asset.map(assetMapper)}
                mapper.map(assetsUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : AssetsUi() {
        override fun map(mapper: AssetsCommunication) =
            mapper.map(listOf(AssetUi.Fail(errorMessage)))
    }
}

