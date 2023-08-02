package com.veselovvv.coinsapp.domain.assethistory

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryData
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryDataToDomainMapper
import com.veselovvv.coinsapp.data.assethistory.AssetsHistoryDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseAssetsHistoryDataToDomainMapper(
    private val assetHistoryMapper: AssetHistoryDataToDomainMapper
) : AssetsHistoryDataToDomainMapper {
    override fun map(assetsHistory: List<AssetHistoryData>) =
        AssetsHistoryDomain.Success(assetsHistory, assetHistoryMapper)

    override fun map(e: Exception) = AssetsHistoryDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}