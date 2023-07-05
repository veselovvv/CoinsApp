package com.veselovvv.coinsapp.data.assets

import com.veselovvv.coinsapp.core.Mapper
import com.veselovvv.coinsapp.domain.assets.AssetsDomain

abstract class AssetsDataToDomainMapper : Mapper.DataToDomain.Base<List<AssetData>, AssetsDomain>()