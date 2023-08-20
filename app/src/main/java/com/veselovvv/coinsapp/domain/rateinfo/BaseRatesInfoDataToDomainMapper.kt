package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.core.ErrorType
import com.veselovvv.coinsapp.data.rateinfo.RateInfoData
import com.veselovvv.coinsapp.data.rateinfo.RateInfoDataToDomainMapper
import com.veselovvv.coinsapp.data.rateinfo.RatesInfoDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseRatesInfoDataToDomainMapper(
    private val mapper: RateInfoDataToDomainMapper
) : RatesInfoDataToDomainMapper {
    override fun map(rateInfo: RateInfoData) = RatesInfoDomain.Success(rateInfo, mapper)
    override fun map(e: Exception) = RatesInfoDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}