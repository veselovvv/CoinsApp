package com.veselovvv.coinsapp.presentation.assetinfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Read
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.assetinfo.AssetsInfoDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetinfo.FetchAssetInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AssetsInfoViewModel @Inject constructor(
    private val communication: AssetsInfoCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchAssetInfoUseCase: FetchAssetInfoUseCase,
    private val mapper: AssetsInfoDomainToUiMapper,
    private val assetCache: Read<Triple<String, String, String>>
) : ViewModel() {
    fun fetchAssetInfo(id: String) {
        communication.map(AssetInfoUi.Progress)
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchAssetInfoUseCase.execute(id)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<AssetInfoUi>) =
        communication.observe(owner, observer)

    fun getAssetId() = assetCache.//TODO
    fun getAssetRank() = assetCache.read().first
    fun getAssetSymbol() = assetCache.read().second
    fun getAssetName() = assetCache.read().third
}