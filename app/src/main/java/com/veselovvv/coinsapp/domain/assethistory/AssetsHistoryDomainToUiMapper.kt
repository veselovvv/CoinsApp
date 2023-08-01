package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType

interface AssetsHistoryDomainToUiMapper {
    fun map(assetsHistory: List<AssetHistoryDomain>): AssetsHistoryUi
    fun map(errorType: ErrorType): AssetsHistoryUi
}