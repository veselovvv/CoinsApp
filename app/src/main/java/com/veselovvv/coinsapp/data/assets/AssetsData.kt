package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assets.AssetsDomain

sealed class AssetsData : Object<AssetsDomain, AssetsDataToDomainMapper> {
    data class Success(private val assets: List<AssetData>) : AssetsData() {
        override fun map(mapper: AssetsDataToDomainMapper) = mapper.map(assets)
    }

    data class Fail(private val exception: Exception) : AssetsData() {
        override fun map(mapper: AssetsDataToDomainMapper) = mapper.map(exception)
    }
}
