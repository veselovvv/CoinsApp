package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryData
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryDataToDomainMapper
import com.veselovvv.coinsapp.presentation.assethistory.AssetsHistoryUi

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
