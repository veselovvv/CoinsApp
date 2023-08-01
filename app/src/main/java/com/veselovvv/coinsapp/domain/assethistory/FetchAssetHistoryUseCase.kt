package com.veselovvv.coinsapp.domain.assethistory

interface FetchAssetHistoryUseCase {
    suspend fun execute(assetId: String): AssetsHistoryDomain

    class Base(
        private val repository: AssetHistoryRepository,
        private val mapper: AssetsHistoryDataToDomainMapper
    ) : FetchAssetHistoryUseCase {
        override suspend fun execute(assetId: String) =
            repository.fetchAssetInfo(assetId).map(mapper)
    }
}