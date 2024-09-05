package com.veselovvv.coinsapp.markets

import com.veselovvv.coinsapp.data.markets.MarketsRepository
import com.veselovvv.coinsapp.di.markets.MarketsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MarketsDataModule::class] // replaces MarketsDataModule with this fake one
)
class TestMarketsDataModule {
    @Provides
    @Singleton
    fun provideMarketsRepository(): MarketsRepository = TestMarketsRepository()
}