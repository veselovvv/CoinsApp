package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.domain.assethistory.AssetsHistoryDomain

interface AssetsHistoryDataToDomainMapper {
    fun map(assetsHistory: List<AssetHistoryData>): AssetsHistoryDomain
    fun map(e: Exception): AssetsHistoryDomain
}