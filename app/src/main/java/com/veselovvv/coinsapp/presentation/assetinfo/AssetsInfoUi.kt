package com.veselovvv.coinsapp.presentation.assetinfo

import com.veselovvv.coinsapp.core.Object
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomain
import com.veselovvv.coinsapp.domain.assetinfo.AssetInfoDomainToUiMapper

sealed class AssetsInfoUi : Object<Unit, AssetsInfoCommunication> {
    data class Success(
        private val assetInfo: AssetInfoDomain,
        private val assetInfoMapper: AssetInfoDomainToUiMapper
    ) : AssetsInfoUi() {
        override fun map(mapper: AssetsInfoCommunication) {
            val assetInfoUi = assetInfo.map(assetInfoMapper)
            mapper.map(assetInfoUi)
        }
    }

    data class Fail(private val text: String) : AssetsInfoUi() {
        override fun map(mapper: AssetsInfoCommunication) =
            mapper.map(AssetInfoUi.Fail(text))
    }
}
