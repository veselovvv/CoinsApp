package com.veselovvv.coinsapp.presentation.assets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.assets.AssetsDomainToUiMapper
import com.veselovvv.coinsapp.domain.assets.FetchAssetsUseCase
import com.veselovvv.coinsapp.domain.assets.SearchAssetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val communication: AssetsCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchAssetsUseCase: FetchAssetsUseCase,
    private val searchAssetsUseCase: SearchAssetsUseCase,
    private val mapper: AssetsDomainToUiMapper,
    private val assetCache: Save<Triple<String, String, String>>
) : ViewModel() {
    fun fetchAssets() {
        communication.map(listOf(AssetUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchAssetsUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun searchAssets(query: String) {
        communication.map(listOf(AssetUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = searchAssetsUseCase.execute(query)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun saveAssetInfo(rank: String, symbol: String, name: String) =
        assetCache.save(Triple(rank, symbol, name))

    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetUi>>) =
        communication.observe(owner, observer)
}