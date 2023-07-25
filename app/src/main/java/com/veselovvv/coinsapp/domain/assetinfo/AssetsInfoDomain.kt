package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.core.Object

sealed class AssetsInfoDomain : Object<AssetsInfoUi, AssetsInfoDomainToUiMapper> {
    data class Success(
        private val assetInfo: AssetInfoData,
        private val assetInfoMapper: AssetInfoDataToDomainMapper
    ) : AssetsInfoDomain() {
        override fun map(mapper: AssetsInfoDomainToUiMapper) =
            mapper.map(assetInfo.map(assetInfoMapper))
    }

    data class Fail(private val error: ErrorType) : AssetsInfoDomain() {
        override fun map(mapper: AssetsInfoDomainToUiMapper) = mapper.map(error)
    }
}
