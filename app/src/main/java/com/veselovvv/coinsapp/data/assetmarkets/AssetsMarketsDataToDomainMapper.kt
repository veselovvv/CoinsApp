package com.veselovvv.coinsapp.data.assetmarkets

import com.veselovvv.coinsapp.domain.assetmarkets.AssetsMarketsDomain

interface AssetsMarketsDataToDomainMapper {
    fun map(assetsMarkets: List<AssetMarketsData>): AssetsMarketsDomain
    fun map(e: Exception): AssetsMarketsDomain
}