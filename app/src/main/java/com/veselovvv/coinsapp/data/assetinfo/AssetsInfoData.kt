package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomain

sealed class AssetsInfoData : Object<AssetsInfoDomain, AssetsInfoDataToDomainMapper> {
    data class Success(private val assetInfo: AssetInfoData) : AssetsInfoData() {
        override fun map(mapper: AssetsInfoDataToDomainMapper) = mapper.map(assetInfo)
    }

    data class Fail(private val exception: Exception) : AssetsInfoData() {
        override fun map(mapper: AssetsInfoDataToDomainMapper) = mapper.map(exception)
    }
}
