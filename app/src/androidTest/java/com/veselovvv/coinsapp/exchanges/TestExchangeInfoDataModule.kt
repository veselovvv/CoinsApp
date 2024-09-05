package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.data.exchangeinfo.ExchangeInfoRepository
import com.veselovvv.coinsapp.di.exchangeinfo.ExchangeInfoDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ExchangeInfoDataModule::class] // replaces ExchangeInfoDataModule with this fake one
)
class TestExchangeInfoDataModule {
    @Provides
    @Singleton
    fun provideExchangeInfoRepository(): ExchangeInfoRepository = TestExchangeInfoRepository()
}