package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assets.AssetData
import com.veselovvv.coinsapp.data.assets.AssetDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseAssetsDataToDomainMapper(
    private val assetMapper: AssetDataToDomainMapper
) : AssetsDataToDomainMapper {
    override fun map(data: List<AssetData>) = AssetsDomain.Success(data, assetMapper)
    override fun map(e: Exception) = AssetsDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}