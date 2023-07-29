package com.veselovvv.coinsapp.di.assetinfo

import android.content.Context
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.core.ResourceProvider
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoDataToDomainMapper
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoRepository
import com.veselovvv.coinsapp.data.assetinfo.AssetsInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetinfo.BaseAssetInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetinfo.BaseAssetsInfoDataToDomainMapper
import com.veselovvv.coinsapp.domain.assetinfo.FetchAssetInfoUseCase
import com.veselovvv.coinsapp.presentation.assetinfo.AssetsInfoCommunication
import com.veselovvv.coinsapp.presentation.assetinfo.BaseAssetInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assetinfo.BaseAssetsInfoDomainToUiMapper
import com.veselovvv.coinsapp.presentation.assets.AssetCache
import com.veselovvv.coinsapp.presentation.assets.AssetParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AssetInfoDomainModule {
    @Provides
    fun provideAssetsInfoCommunication(): AssetsInfoCommunication = AssetsInfoCommunication.Base()

    @Provides
    fun provideAssetInfoDataToDomainMapper(): AssetInfoDataToDomainMapper = BaseAssetInfoDataToDomainMapper()

    @Provides
    fun provideAssetsInfoDataToDomainMapper(
        assetInfoMapper: AssetInfoDataToDomainMapper
    ): AssetsInfoDataToDomainMapper = BaseAssetsInfoDataToDomainMapper(assetInfoMapper)

    @Provides
    fun provideFetchAssetInfoUseCase(
        repository: AssetInfoRepository,
        mapper: AssetsInfoDataToDomainMapper
    ): FetchAssetInfoUseCase = FetchAssetInfoUseCase.Base(repository, mapper)

    @Provides
    fun provideAssetInfoDomainToUiMapper(): AssetInfoDomainToUiMapper = BaseAssetInfoDomainToUiMapper()

    @Provides
    fun provideAssetsInfoDomainToUiMapper(
        resourceProvider: ResourceProvider,
        assetInfoMapper: AssetInfoDomainToUiMapper
    ): AssetsInfoDomainToUiMapper = BaseAssetsInfoDomainToUiMapper(resourceProvider, assetInfoMapper)

    @Provides
    fun provideAssetCache(
        @ApplicationContext context: Context
    ): Read<AssetParameters> = AssetCache.Base(context)
}