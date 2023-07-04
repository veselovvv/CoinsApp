package com.veselovvv.coinsapp.domain.assets

import com.veselovvv.coinsapp.core.Mapper
import com.veselovvv.coinsapp.core.ResourceProvider

abstract class AssetsDomainToUiMapper(resourceProvider: ResourceProvider)
    : Mapper.DomainToUi.Base<List<AssetDomain>, AssetsUi>(resourceProvider)