package com.veselovvv.coinsapp.domain.assetinfo

interface FetchAssetInfoUseCase {
    suspend fun execute(id: String): AssetsInfoDomain

    class Base(
        private val repository: AssetInfoRepository,
        private val mapper: AssetsInfoDataToDomainMapper
    ) : FetchAssetInfoUseCase {
        override suspend fun execute(id: String) = repository.fetchAssetInfo(id).map(mapper)
    }
}