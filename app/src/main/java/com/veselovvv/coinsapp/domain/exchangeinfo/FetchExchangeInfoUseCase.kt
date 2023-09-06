package com.veselovvv.coinsapp.domain.exchangeinfo

interface FetchExchangeInfoUseCase {
    suspend fun execute(id: String): ExchangesInfoDomain

    class Base(
        private val repository: ExchangeInfoRepository,
        private val mapper: ExchangesInfoDataToDomainMapper
    ) : FetchExchangeInfoUseCase {
        override suspend fun execute(id: String) = repository.fetchExchangeInfo(id).map(mapper)
    }
}