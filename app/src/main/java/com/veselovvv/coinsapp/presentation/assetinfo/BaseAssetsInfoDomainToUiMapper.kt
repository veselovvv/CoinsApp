package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomain
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomainToUiMapper

class BaseAssetsInfoDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: AssetInfoDomainToUiMapper
) : AssetsInfoDomainToUiMapper {
    override fun map(assetInfo: AssetInfoDomain) = AssetsInfoUi.Success(assetInfo, mapper)
    override fun map(errorType: ErrorType) = AssetsInfoUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}