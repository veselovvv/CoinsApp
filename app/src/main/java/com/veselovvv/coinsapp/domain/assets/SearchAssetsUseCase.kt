package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsRepository

interface SearchAssetsUseCase {
    suspend fun execute(query: String): AssetsDomain

    class Base(
        private val repository: AssetsRepository,
        private val mapper: AssetsDataToDomainMapper
    ) : SearchAssetsUseCase {
        override suspend fun execute(query: String) = repository.searchAssets(query).map(mapper)
    }
}