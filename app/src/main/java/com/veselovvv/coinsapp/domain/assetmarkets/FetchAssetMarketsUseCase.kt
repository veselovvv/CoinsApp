package com.veselovvv.coinsapp.domain.assetmarkets

interface FetchAssetMarketsUseCase {
    suspend fun execute(assetId: String): AssetsMarketsDomain

    class Base(
        private val repository: AssetMarketsRepository,
        private val mapper: AssetsMarketsDataToDomainMapper
    ) : FetchAssetMarketsUseCase {
        override suspend fun execute(assetId: String) =
            repository.fetchAssetMarkets(assetId).map(mapper)
    }
}