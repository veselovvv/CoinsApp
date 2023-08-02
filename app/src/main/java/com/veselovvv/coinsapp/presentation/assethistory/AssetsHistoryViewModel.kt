package com.veselovvv.coinsapp.presentation.assethistory

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.coinsapp.di.core.CoreDomainModule
import com.veselovvv.coinsapp.domain.assethistory.AssetsHistoryDomainToUiMapper
import com.veselovvv.coinsapp.domain.assethistory.FetchAssetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AssetsHistoryViewModel @Inject constructor(
    private val communication: AssetsHistoryCommunication,
    @CoreDomainModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersUi: CoroutineDispatcher,
    private val fetchAssetHistoryUseCase: FetchAssetHistoryUseCase,
    private val mapper: AssetsHistoryDomainToUiMapper
) : ViewModel() {
    fun fetchAssetHistory(assetId: String) {
        communication.map(listOf(AssetHistoryUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val resultDomain = fetchAssetHistoryUseCase.execute(assetId)
            val resultUi = resultDomain.map(mapper)
            withContext(dispatchersUi) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<AssetHistoryUi>>) =
        communication.observe(owner, observer)
}