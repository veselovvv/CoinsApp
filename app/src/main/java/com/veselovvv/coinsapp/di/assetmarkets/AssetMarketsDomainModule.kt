package com.veselovvv.coinsapp.di.assetmarkets

import android.content.Context
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsDataToDomainMapper
import com.veselovvv.coinsapp.data.assetmarkets.AssetMarketsRepository
import com.veselovvv.coinsapp.data.assetmarkets.AssetsMarketsDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetmarkets.AssetMarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetmarkets.AssetsMarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetmarkets.BaseAssetMarketsDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetmarkets.BaseAssetsMarketsDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetmarkets.FetchAssetMarketsUseCase
import com.veselovvv.coinsapp.domain.assetmarkets.SearchAssetMarketsUseCase
import com.veselovvv.coinsapp.presentation.assetmarkets.AssetMarketsCache
import com.veselovvv.coinsapp.presentation.assetmarkets.AssetsMarketsCommunication
import com.veselovvv.coinsapp.presentation.assetmarkets.BaseAssetMarketsDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assetmarkets.BaseAssetsMarketsDomainToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AssetMarketsDomainModule {
    @Provides
    fun provideAssetsMarketsCommunication(): AssetsMarketsCommunication =
        AssetsMarketsCommunication.Base()

    @Provides
    fun provideAssetMarketsDataToDomainMapper(): AssetMarketsDataToDomainMapper =
        BaseAssetMarketsDataToDomainMapper()

    @Provides
    fun provideAssetsMarketsDataToDomainMapper(
        assetMarketsMapper: AssetMarketsDataToDomainMapper
    ): AssetsMarketsDataToDomainMapper = BaseAssetsMarketsDataToDomainMapper(assetMarketsMapper)

    @Provides
    fun provideFetchAssetMarketsUseCase(
        repository: AssetMarketsRepository,
        mapper: AssetsMarketsDataToDomainMapper
    ): FetchAssetMarketsUseCase = FetchAssetMarketsUseCase.Base(repository, mapper)

    @Provides
    fun provideSearchAssetMarketsUseCase(
        repository: AssetMarketsRepository,
        mapper: AssetsMarketsDataToDomainMapper
    ): SearchAssetMarketsUseCase = SearchAssetMarketsUseCase.Base(repository, mapper)

    @Provides
    fun provideAssetMarketsDomainToUiMapper(): AssetMarketsDomainToUiMapper =
        BaseAssetMarketsDomainToUiMapper()

    @Provides
    fun provideAssetsMarketsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        assetMarketsMapper: AssetMarketsDomainToUiMapper
    ): AssetsMarketsDomainToUiMapper = BaseAssetsMarketsDomainToUiMapper(resourceProvider, assetMarketsMapper)

    @Provides
    fun provideAssetMarketsCache(
        @ApplicationContext context: Context
    ): Save<Triple<String, String, String>> = AssetMarketsCache.Base(context)
}