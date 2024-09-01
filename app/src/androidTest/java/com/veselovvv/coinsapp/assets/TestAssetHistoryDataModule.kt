package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assethistory.AssetHistoryRepository
import com.veselovvv.coinsapp.di.assethistory.AssetHistoryDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AssetHistoryDataModule::class] // replaces AssetHistoryDataModule with this fake one
)
class TestAssetHistoryDataModule {
    @Provides
    @Singleton
    fun provideAssetHistoryRepository(): AssetHistoryRepository = TestAssetHistoryRepository()
}