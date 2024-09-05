package com.veselovvv.coinsapp.assets

import com.veselovvv.coinsapp.data.assetinfo.AssetInfoData
import com.veselovvv.coinsapp.data.assetinfo.AssetInfoRepository
import com.veselovvv.coinsapp.data.assetinfo.AssetsInfoData
import java.net.UnknownHostException

class TestAssetInfoRepository : AssetInfoRepository {
    private var isSuccess = false

    override suspend fun fetchAssetInfo(id: String): AssetsInfoData {
        isSuccess = !isSuccess

        return if (isSuccess) AssetsInfoData.Success(
            AssetInfoData(
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        ) else AssetsInfoData.Fail(UnknownHostException())
    }
}