package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.presentation.assets.AssetsUi

interface AssetsDomainToUiMapper {
    fun map(assets: List<AssetDomain>): AssetsUi
    fun map(errorType: ErrorType): AssetsUi
}