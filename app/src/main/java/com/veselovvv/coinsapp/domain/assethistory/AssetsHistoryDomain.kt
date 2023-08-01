package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object

sealed class AssetsHistoryDomain : Object<AssetsHistoryUi, AssetsHistoryDomainToUiMapper> {
    data class Success(
        private val assetsHistory: List<AssetHistoryData>,
        private val assetHistoryMapper: AssetHistoryDataToDomainMapper
    ) : AssetsHistoryDomain() {
        override fun map(mapper: AssetsHistoryDomainToUiMapper) = mapper.map(
            assetsHistory.map { assetHistory ->
                assetHistory.map(assetHistoryMapper)
            }
        )
    }

    data class Fail(private val error: ErrorType) : AssetsHistoryDomain() {
        override fun map(mapper: AssetsHistoryDomainToUiMapper) = mapper.map(error)
    }
}
