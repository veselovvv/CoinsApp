package com.veselovvv.coinsapp.exchanges

import com.veselovvv.coinsapp.data.exchanges.ExchangesRepository
import com.veselovvv.coinsapp.di.exchanges.ExchangesDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ExchangesDataModule::class] // replaces ExchangesDataModule with this fake one
)
class TestExchangesDataModule {
    @Provides
    @Singleton
    fun provideExchangesRepository(): ExchangesRepository = TestExchangesRepository()
}