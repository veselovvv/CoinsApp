package com.veselovvv.coinsapp.domain.rates

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rates.RateData
import com.veselovvv.coinsapp.data.rates.RateDataToDomainMapper
import com.veselovvv.coinsapp.data.rates.RatesDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseRatesDataToDomainMapper(
    private val rateMapper: RateDataToDomainMapper
) : RatesDataToDomainMapper {
    override fun map(rates: List<RateData>) = RatesDomain.Success(rates, rateMapper)
    override fun map(e: Exception) = RatesDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}