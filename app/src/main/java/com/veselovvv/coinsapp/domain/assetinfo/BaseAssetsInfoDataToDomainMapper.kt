package com.veselovvv.coinsapp.domain.assetinfo

import com.veselovvv.coinsapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseAssetsInfoDataToDomainMapper(
    private val mapper: AssetInfoDataToDomainMapper
) : AssetsInfoDataToDomainMapper {
    override fun map(assetInfo: AssetInfoData) = AssetsInfoDomain.Success(assetInfo, mapper)
    override fun map(e: Exception) = AssetsInfoDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}