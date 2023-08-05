package com.veselovvv.coinsapp.domain.assetmarkets

interface SearchAssetMarketsUseCase {
    suspend fun execute(assetId: String, query: String): AssetsMarketsDomain

    class Base(
        private val repository: AssetMarketsRepository,
        private val mapper: AssetsMarketsDataToDomainMapper
    ) : SearchAssetMarketsUseCase {
        override suspend fun execute(assetId: String, query: String) =
            repository.searchAssetMarkets(assetId, query).map(mapper)
    }
}