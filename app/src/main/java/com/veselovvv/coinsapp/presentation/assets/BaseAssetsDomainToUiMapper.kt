package com.veselovvv.coinsapp.presentation.assets

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.assets.AssetDomain
import com.veselovvv.coinsapp.domain.assets.AssetDomainToUiMapper
import com.veselovvv.coinsapp.domain.assets.AssetsDomainToUiMapper

class BaseAssetsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val assetMapper: AssetDomainToUiMapper
) : AssetsDomainToUiMapper {
    override fun map(assets: List<AssetDomain>) = AssetsUi.Success(assets, assetMapper)
    override fun map(errorType: ErrorType) = AssetsUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}