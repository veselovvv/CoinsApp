package com.veselovvv.coinsapp.domain.exchangeinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoData
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoDataToDomainMapper
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangesInfoDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseExchangesInfoDataToDomainMapper(
    private val mapper: ExchangeInfoDataToDomainMapper
) : ExchangesInfoDataToDomainMapper {
    override fun map(exchangeInfo: ExchangeInfoData) = ExchangesInfoDomain.Success(exchangeInfo, mapper)
    override fun map(e: Exception) = ExchangesInfoDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}