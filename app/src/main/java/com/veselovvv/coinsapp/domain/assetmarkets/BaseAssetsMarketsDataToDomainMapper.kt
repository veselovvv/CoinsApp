package com.veselovvv.coinsapp.domain.assetmarkets

import com.veselovvv.coinsapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseAssetsMarketsDataToDomainMapper(
    private val assetMarketsMapper: AssetMarketsDataToDomainMapper
) : AssetsMarketsDataToDomainMapper {
    override fun map(assetsMarkets: List<AssetMarketsData>) =
        AssetsMarketsDomain.Success(assetsMarkets, assetMarketsMapper)

    override fun map(e: Exception) = AssetsMarketsDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}