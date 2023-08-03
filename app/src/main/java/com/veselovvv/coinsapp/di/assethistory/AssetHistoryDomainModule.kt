package com.veselovvv.coinsapp.di.assethistory

import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryDataToDomainMapper
import com.veselovvv.coinsapp.data.assethistory.AssetHistoryRepository
import com.veselovvv.coinsapp.data.assethistory.AssetsHistoryDataToDomainMapper
import com.veselovvv.coinsapp.domain.assethistory.AssetHistoryDomainToUiMapper
import com.veselovvv.coinsapp.domain.assethistory.AssetsHistoryDomainToUiMapper
import com.veselovvv.coinsapp.domain.assethistory.BaseAssetHistoryDataToDomainMapper
import com.veselovvv.coinsapp.domain.assethistory.BaseAssetsHistoryDataToDomainMapper
import com.veselovvv.coinsapp.domain.assethistory.FetchAssetHistoryUseCase
import com.veselovvv.coinsapp.presentation.assethistory.AssetsHistoryCommunication
import com.veselovvv.coinsapp.presentation.assethistory.BaseAssetHistoryDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assethistory.BaseAssetsHistoryDomainToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AssetHistoryDomainModule {
    @Provides
    fun provideAssetsHistoryCommunication(): AssetsHistoryCommunication =
        AssetsHistoryCommunication.Base()

    @Provides
    fun provideAssetHistoryDataToDomainMapper(): AssetHistoryDataToDomainMapper =
        BaseAssetHistoryDataToDomainMapper()

    @Provides
    fun provideAssetsHistoryDataToDomainMapper(
        assetHistoryMapper: AssetHistoryDataToDomainMapper
    ): AssetsHistoryDataToDomainMapper = BaseAssetsHistoryDataToDomainMapper(assetHistoryMapper)

    @Provides
    fun provideFetchAssetHistoryUseCase(
        repository: AssetHistoryRepository,
        assetsHistoryMapper:AssetsHistoryDataToDomainMapper
    ): FetchAssetHistoryUseCase = FetchAssetHistoryUseCase.Base(repository, assetsHistoryMapper)

    @Provides
    fun provideAssetHistoryDomainToUiMapper(): AssetHistoryDomainToUiMapper =
        BaseAssetHistoryDomainToUiMapper()

    @Provides
    fun provideAssetsHistoryDomainToUiMapper(
        resourceProvider: ResourceProvider,
        assetHistoryMapper: AssetHistoryDomainToUiMapper
    ): AssetsHistoryDomainToUiMapper =
        BaseAssetsHistoryDomainToUiMapper(resourceProvider, assetHistoryMapper)
}