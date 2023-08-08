package com.veselovvv.coinsapp.presentation.assetmarkets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.AssetMarketsLayoutBinding
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding

class AssetMarketsAdapter(
    private val retry: Retry
) : RecyclerView.Adapter<AssetMarketsAdapter.AssetMarketsViewHolder>() {
    private val assetMarkets = ArrayList<AssetMarketsUi>()

    fun update(newList: List<AssetMarketsUi>) {
        assetMarkets.clear()
        assetMarkets.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (assetMarkets[position]) {
        is AssetMarketsUi.NoResults -> -1
        is AssetMarketsUi.Base -> 0
        is AssetMarketsUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> AssetMarketsViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> AssetMarketsViewHolder.Base(
            AssetMarketsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        1 -> AssetMarketsViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> AssetMarketsViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetMarketsViewHolder, position: Int) =
        holder.bind(assetMarkets[position])

    override fun getItemCount() = assetMarkets.size

    abstract class AssetMarketsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(assetMarkets: AssetMarketsUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : AssetMarketsViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : AssetMarketsViewHolder(binding.root)

        class Base(
            private val binding: AssetMarketsLayoutBinding
        ) : AssetMarketsViewHolder(binding.root) {
            override fun bind(assetMarkets: AssetMarketsUi) {
                assetMarkets.map(object : AssetMarketsUi.BaseMapper {
                    override fun map(exchangeId: String, baseId: String, quoteId: String) {
                        with(binding) {
                            assetMarketsExchangeIdTextView.text = exchangeId
                            assetMarketsBaseIdTextView.text = baseId
                            assetMarketsQuoteIdTextView.text = quoteId
                        }
                    }

                    override fun map(text: String) = Unit
                })
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : AssetMarketsViewHolder(binding.root) {
            override fun bind(assetMarkets: AssetMarketsUi) {
                assetMarkets.map(object : AssetMarketsUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(exchangeId: String, baseId: String, quoteId: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }
}