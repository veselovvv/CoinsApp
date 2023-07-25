package com.veselovvv.coinsapp.domain.assetinfo

class TestAssetInfoRepository(private val exception: Exception? = null) : AssetInfoRepository {
    override suspend fun fetchAssetInfo(id: String) = if (exception == null)
        AssetsInfoData.Success(
            AssetInfoData(
                supply = "17193925.0000000000000000",
                maxSupply = "21000000.0000000000000000",
                marketCapUsd = "119179791817.6740161068269075",
                volumeUsd24Hr = "2928356777.6066665425687196",
                priceUsd = "6931.5058555666618359",
                changePercent24Hr = "-0.8101417214350335",
                vwap24Hr = "7175.0663247679233209"
            )
        ) else AssetsInfoData.Fail(exception)
}