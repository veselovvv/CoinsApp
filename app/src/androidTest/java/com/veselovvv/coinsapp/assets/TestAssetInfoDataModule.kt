package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assetinfo.AssetInfoRepository
import com.veselovvv.coinsapp.di.assetinfo.AssetInfoDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AssetInfoDataModule::class] // replaces AssetInfoDataModule with this fake one
)
class TestAssetInfoDataModule {
    @Provides
    @Singleton
    fun provideAssetInfoRepository(): AssetInfoRepository = TestAssetInfoRepository()
}