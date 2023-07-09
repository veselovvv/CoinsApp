package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsRepository

class FetchAssetsUseCase(
    private val repository: AssetsRepository,
    private val mapper: AssetsDataToDomainMapper
) {
    suspend fun execute() = repository.fetchAssets().map(mapper)
}