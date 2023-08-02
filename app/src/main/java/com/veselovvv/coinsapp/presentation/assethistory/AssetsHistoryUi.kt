package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomain
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomainToUiMapper

sealed class AssetsHistoryUi : Object<Unit, AssetsHistoryCommunication> {
    data class Success(
        private val assetsHistory: List<AssetHistoryDomain>,
        private val assetHistoryMapper: AssetHistoryDomainToUiMapper
    ) : AssetsHistoryUi() {
        override fun map(mapper: AssetsHistoryCommunication) {
            if (assetsHistory.isEmpty())
                mapper.map(listOf(AssetHistoryUi.NoResults))
            else {
                val assetsHistoryUi = assetsHistory.map { assetHistory ->
                    assetHistory.map(assetHistoryMapper)
                }
                mapper.map(assetsHistoryUi)
            }
        }
    }

    data class Fail(private val errorMessage: String) : AssetsHistoryUi() {
        override fun map(mapper: AssetsHistoryCommunication) =
            mapper.map(listOf(AssetHistoryUi.Fail(errorMessage)))
    }
}
