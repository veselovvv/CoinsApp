package com.veselovvv.coinsapp.rates

import com.veselovvv.coinsapp.data.rateinfo.RateInfoRepository
import com.veselovvv.coinsapp.di.rateinfo.RateInfoDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RateInfoDataModule::class] // replaces RateInfoDataModule with this fake one
)
class TestRateInfoDataModule {
    @Provides
    @Singleton
    fun provideRateInfoRepository(): RateInfoRepository = TestRateInfoRepository()
}