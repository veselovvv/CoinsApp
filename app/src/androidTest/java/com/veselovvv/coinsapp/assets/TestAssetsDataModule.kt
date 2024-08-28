package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assets.AssetsRepository
import com.veselovvv.coinsapp.di.assets.AssetsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AssetsDataModule::class] // replaces AssetsDataModule with this fake one
)
class TestAssetsDataModule {
    @Provides
    @Singleton
    fun provideAssetsRepository(): AssetsRepository = TestAssetsRepository()
}