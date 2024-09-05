package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.data.rates.RatesRepository
import com.veselovvv.coinsapp.di.rates.RatesDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RatesDataModule::class] // replaces RatesDataModule with this fake one
)
class TestRatesDataModule {
    @Provides
    @Singleton
    fun provideRatesRepository(): RatesRepository = TestRatesRepository()
}