package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.domain.assets.AssetsDomain

interface AssetsDataToDomainMapper {
    fun map(assets: List<AssetData>): AssetsDomain
    fun map(e: Exception): AssetsDomain
}