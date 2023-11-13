package com.veselovvv.coinsapp.presentation.markets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.MarketLayoutBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding

class MarketsAdapter(
    private val retry: Retry
) : RecyclerView.Adapter<MarketsAdapter.MarketsViewHolder>() {
    private val markets = ArrayList<MarketUi>()

    fun update(newList: List<MarketUi>) {
        markets.clear()
        markets.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (markets[position]) {
        is MarketUi.NoResults -> -1
        is MarketUi.Base -> 0
        is MarketUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> MarketsViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> MarketsViewHolder.Base(
            MarketLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        1 -> MarketsViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> MarketsViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarketsViewHolder, position: Int) =
        holder.bind(markets[position])

    override fun getItemCount() = markets.size

    abstract class MarketsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(market: MarketUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : MarketsViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : MarketsViewHolder(binding.root)

        class Base(
            private val binding: MarketLayoutBinding
        ) : MarketsViewHolder(binding.root) {
            override fun bind(market: MarketUi) {
                market.map(object : MarketUi.BaseMapper {
                    override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) {
                        with(binding) {
                            exchangeIdTextView.text = exchangeId
                            baseSymbolTextView.text = baseSymbol
                            quoteSymbolTextView.text = quoteSymbol
                        }
                    }

                    override fun map(text: String) = Unit
                })
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : MarketsViewHolder(binding.root) {
            override fun bind(market: MarketUi) {
                market.map(object : MarketUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(exchangeId: String, baseSymbol: String, quoteSymbol: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }
}