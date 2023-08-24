package com.veselovvv.coinsapp.domain.exchanges

import com.veselovvv.coinsapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseExchangesDataToDomainMapper(
    private val exchangeMapper: ExchangeDataToDomainMapper
) : ExchangesDataToDomainMapper {
    override fun map(exchanges: List<ExchangeData>) = ExchangesDomain.Success(exchanges, exchangeMapper)
    override fun map(e: Exception) = ExchangesDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}