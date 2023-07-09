package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.assets.AssetDomain
import com.veselovvv.coinsapp.domain.assets.AssetDomainToUiMapper
import com.veselovvv.coinsapp.domain.assets.AssetsDomainToUiMapper

class BaseAssetsDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val assetMapper: AssetDomainToUiMapper
) : AssetsDomainToUiMapper(resourceProvider) {
    override fun map(data: List<AssetDomain>) = AssetsUi.Success(data, assetMapper)
    override fun map(errorType: ErrorType) = AssetsUi.Fail(getErrorMessage(errorType)) //TODO fix public
}