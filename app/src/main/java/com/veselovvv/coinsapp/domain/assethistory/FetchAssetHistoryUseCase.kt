package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.data.assethistory.AssetHistoryRepository
import com.veselovvv.coinsapp.data.assethistory.AssetsHistoryDataToDomainMapper

interface FetchAssetHistoryUseCase {
    suspend fun execute(assetId: String): AssetsHistoryDomain

    class Base(
        private val repository: AssetHistoryRepository,
        private val mapper: AssetsHistoryDataToDomainMapper
    ) : FetchAssetHistoryUseCase {
        override suspend fun execute(assetId: String) =
            repository.fetchAssetHistory(assetId).map(mapper)
    }
}