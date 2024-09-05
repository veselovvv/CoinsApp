package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsRepository
import com.veselovvv.coinsapp.di.assetmarkets.AssetMarketsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AssetMarketsDataModule::class] // replaces AssetMarketsDataModule with this fake one
)
class TestAssetMarketsDataModule {
    @Provides
    @Singleton
    fun provideAssetMarketsRepository(): AssetMarketsRepository = TestAssetMarketsRepository()
}