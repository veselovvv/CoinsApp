package com.veselovvv.coinsapp.domain.assets

class FetchAssetsUseCase(
    private val repository: AssetsRepository,
    private val mapper: AssetsDataToDomainMapper
) {
    suspend fun execute() = repository.fetchAssets().map(mapper)
}