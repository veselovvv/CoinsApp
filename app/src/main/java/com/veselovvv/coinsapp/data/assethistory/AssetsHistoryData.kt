package com.veselovvv.coinsapp.data.assethistory

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assethistory.AssetsHistoryDomain

sealed class AssetsHistoryData : Object<AssetsHistoryDomain, AssetsHistoryDataToDomainMapper> {
    data class Success(
        private val assetsHistory: List<AssetHistoryData>
    ) : AssetsHistoryData() {
        override fun map(mapper: AssetsHistoryDataToDomainMapper) = mapper.map(assetsHistory)
    }

    data class Fail(private val exception: Exception) : AssetsHistoryData() {
        override fun map(mapper: AssetsHistoryDataToDomainMapper) = mapper.map(exception)
    }
}
