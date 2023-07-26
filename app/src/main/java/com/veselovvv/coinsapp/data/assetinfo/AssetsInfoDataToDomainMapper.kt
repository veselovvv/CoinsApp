package com.veselovvv.coinsapp.data.assetinfo

import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomain

interface AssetsInfoDataToDomainMapper {
    fun map(assetInfo: AssetInfoData): AssetsInfoDomain
    fun map(e: Exception): AssetsInfoDomain
}