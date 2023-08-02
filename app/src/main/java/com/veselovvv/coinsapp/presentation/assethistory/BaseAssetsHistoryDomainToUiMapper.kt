package com.veselovvv.coinsapp.presentation.assethistory

import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomain
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomainToUiMapper
import com.veselovvv.coinsapp.domain.assethistory.AssetsHistoryDomainToUiMapper

class BaseAssetsHistoryDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val assetHistoryMapper: AssetHistoryDomainToUiMapper
) : AssetsHistoryDomainToUiMapper {
    override fun map(assetsHistory: List<AssetHistoryDomain>) =
        AssetsHistoryUi.Success(assetsHistory, assetHistoryMapper)

    override fun map(errorType: ErrorType) = AssetsHistoryUi.Fail(
        resourceProvider.getString(
            when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
        )
    )
}