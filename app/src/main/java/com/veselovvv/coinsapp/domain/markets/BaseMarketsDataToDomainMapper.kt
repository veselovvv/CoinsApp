package com.veselovvv.coinsapp.domain.markets

import com.veselovvv.coinsapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseMarketsDataToDomainMapper(
    private val marketMapper: MarketDataToDomainMapper
) : MarketsDataToDomainMapper{
    override fun map(markets: List<MarketData>) = MarketsDomain.Success(markets, marketMapper)
    override fun map(e: Exception) = MarketsDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}