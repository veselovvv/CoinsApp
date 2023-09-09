package com.veselovvv.coinsapp.di.exchangeinfo

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoDataToDomainMapper
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoRepository
import com.veselovvv.coinsapp.data.exchangeinfo.ExchangesInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.BaseExchangeInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.BaseExchangesInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangeInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.ExchangesInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.exchangeinfo.FetchExchangeInfoUseCase
import com.veselovvv.coinsapp.presentation.exchangeinfo.BaseExchangeInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchangeinfo.BaseExchangesInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.exchangeinfo.ExchangesInfoCommunication
import com.veselovvv.coinsapp.presentation.exchanges.ExchangeCache
import com.veselovvv.coinsapp.presentation.exchanges.ExchangesParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class ExchangeInfoDomainModule {
    @Provides
    fun provideExchangesInfoCommunication(): ExchangesInfoCommunication =
        ExchangesInfoCommunication.Base()

    @Provides
    fun provideExchangeInfoDataToDomainMapper(): ExchangeInfoDataToDomainMapper =
        BaseExchangeInfoDataToDomainMapper()

    @Provides
    fun provideExchangesInfoDataToDomainMapper(
        exchangeInfoMapper: ExchangeInfoDataToDomainMapper
    ): ExchangesInfoDataToDomainMapper = BaseExchangesInfoDataToDomainMapper(exchangeInfoMapper)

    @Provides
    fun provideFetchExchangeInfoUseCase(
        repository: ExchangeInfoRepository,
        mapper: ExchangesInfoDataToDomainMapper
    ): FetchExchangeInfoUseCase = FetchExchangeInfoUseCase.Base(repository, mapper)

    @Provides
    fun provideExchangeInfoDomainToUiMapper(): ExchangeInfoDomainToUiMapper =
        BaseExchangeInfoDomainToUiMapper()

    @Provides
    fun provideExchangesInfoDomainToUiMapper(
        resourceProvider: ResourceProvider,
        exchangeInfoMapper: ExchangeInfoDomainToUiMapper
    ): ExchangesInfoDomainToUiMapper =
        BaseExchangesInfoDomainToUiMapper(resourceProvider, exchangeInfoMapper)

    @Provides
    fun provideExchangeCache(
        @ApplicationContext context: Context
    ): Read<ExchangesParameters> = ExchangeCache.Base(context)
}