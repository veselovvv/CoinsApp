package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsRepository

interface FetchAssetsUseCase {
    suspend fun execute(): AssetsDomain

    class Base(
        private val repository: AssetsRepository,
        private val mapper: AssetsDataToDomainMapper
    ) : FetchAssetsUseCase {
        override suspend fun execute() = repository.fetchAssets().map(mapper)
    }
}