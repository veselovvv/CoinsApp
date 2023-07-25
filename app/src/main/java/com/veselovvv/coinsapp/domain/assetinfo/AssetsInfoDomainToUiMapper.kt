package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType

interface AssetsInfoDomainToUiMapper {
    fun map(assetInfo: AssetInfoDomain): AssetsInfoUi
    fun map(errorType: ErrorType): AssetsInfoUi
}