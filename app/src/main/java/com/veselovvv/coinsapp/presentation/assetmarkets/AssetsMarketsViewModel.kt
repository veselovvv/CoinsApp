package com.veselovvv.coinsapp.presentation.assetmarkets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.core.Save
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.assetmarkets.AssetsMarketsDomainToUiMapper
import com.veselovvv.coinsapp.domain.assetmarkets.FetchAssetMarketsUseCase
import com.veselovvv.coinsapp.domain.assetmarkets.SearchAssetMarketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AssetsMarketsViewModel @Inject constructor(
    private val communication: AssetsMarketsCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchAssetMarketsUseCase: FetchAssetMarketsUseCase,
    private val searchAssetMarketsUseCase: SearchAssetMarketsUseCase,
    private val mapper: AssetsMarketsDomainToUiMapper,
    private val assetMarketsCache: Save<Triple<String, String, String>>
) : ViewModel() {
    fun fetchAssetMarkets(assetId: String) {
        communication.map(listOf(AssetMarketsUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchAssetMarketsUseCase.execute(assetId)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun searchAssetMarkets(assetId: String, query: String) {
        communication.map(listOf(AssetMarketsUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = searchAssetMarketsUseCase.execute(assetId, query)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun saveAssetMarketsInfo(exchangeId: String, baseId: String, quoteId: String) =
        assetMarketsCache.save(Triple(exchangeId, baseId, quoteId))

    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetMarketsUi>>) =
        communication.observe(owner, observer)
}