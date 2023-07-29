package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.data.assetinfo.AssetInfoRepository
import com.veselovvv.coinsapp.data.assetinfo.AssetsInfoDataToDomainMapper

interface FetchAssetInfoUseCase {
    suspend fun execute(id: String): AssetsInfoDomain

    class Base(
        private val repository: AssetInfoRepository,
        private val mapper: AssetsInfoDataToDomainMapper
    ) : FetchAssetInfoUseCase {
        override suspend fun execute(id: String) = repository.fetchAssetInfo(id).map(mapper)
    }
}