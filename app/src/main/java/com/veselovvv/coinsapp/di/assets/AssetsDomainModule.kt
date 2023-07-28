package com.veselovvv.coinsapp.di.assets

import android.content.Context
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.data.assets.AssetDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsDataToDomainMapper
import com.veselovvv.coinsapp.data.assets.AssetsRepository
import com.veselovvv.coinsapp.domain.assets.*
import com.veselovvv.coinsapp.presentation.assets.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AssetsDomainModule {
    @Provides
    fun provideAssetsCommunication(): AssetsCommunication = AssetsCommunication.Base()

    @Provides
    fun provideAssetDataToDomainMapper(): AssetDataToDomainMapper = BaseAssetDataToDomainMapper()

    @Provides
    fun provideAssetsDataToDomainMapper(
        assetMapper: AssetDataToDomainMapper
    ): AssetsDataToDomainMapper = BaseAssetsDataToDomainMapper(assetMapper)

    @Provides
    fun provideFetchAssetsUseCase(
        repository: AssetsRepository,
        assetsMapper: AssetsDataToDomainMapper
    ): FetchAssetsUseCase = FetchAssetsUseCase.Base(repository, assetsMapper)

    @Provides
    fun provideSearchAssetsUseCase(
        repository: AssetsRepository,
        assetsMapper: AssetsDataToDomainMapper
    ): SearchAssetsUseCase = SearchAssetsUseCase.Base(repository, assetsMapper)

    @Provides
    fun provideAssetDomainToUiMapper(): AssetDomainToUiMapper = BaseAssetDomainToUiMapper()

    @Provides
    fun provideAssetsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        assetMapper: AssetDomainToUiMapper
    ): AssetsDomainToUiMapper = BaseAssetsDomainToUiMapper(resourceProvider, assetMapper)

    @Provides
    fun provideAssetCache(
        @ApplicationContext context: Context
    ): Save<AssetParameters> = AssetCache.Base(context)
}