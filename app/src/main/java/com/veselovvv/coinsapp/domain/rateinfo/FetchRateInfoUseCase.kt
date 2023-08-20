package com.veselovvv.coinsapp.domain.rateinfo

import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.data.rateinfo.RatesInfoDataToDomainMapper

interface FetchRateInfoUseCase {
    suspend fun execute(id: String): RatesInfoDomain

    class Base(
        private val repository: RateInfoRepository,
        private val mapper: RatesInfoDataToDomainMapper
    ) : FetchRateInfoUseCase {
        override suspend fun execute(id: String) = repository.fetchRateInfo(id).map(mapper)
    }
}