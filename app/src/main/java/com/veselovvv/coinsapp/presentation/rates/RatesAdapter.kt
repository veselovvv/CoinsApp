package com.veselovvv.coinsapp.presentation.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding
import com.veselovvv.coinsapp.databinding.RateLayoutBinding

class RatesAdapter(
    private val retry: Retry,
    private val rateListener: RateListener
) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    private val rates = ArrayList<RateUi>()

    fun update(newList: List<RateUi>) {
        rates.clear()
        rates.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (rates[position]) {
        is RateUi.NoResults -> -1
        is RateUi.Base -> 0
        is RateUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> RatesViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> RatesViewHolder.Base(
            RateLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            rateListener
        )
        1 -> RatesViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> RatesViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) =
        holder.bind(rates[position])

    override fun getItemCount() = rates.size

    abstract class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(rate: RateUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : RatesViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : RatesViewHolder(binding.root)

        class Base(
            private val binding: RateLayoutBinding,
            private val rateListener: RateListener
        ) : RatesViewHolder(binding.root) {
            override fun bind(rate: RateUi) {
                rate.map(object : RateUi.BaseMapper {
                    override fun map(id: String, symbol: String, rateUsd: String) {
                        with(binding) {
                            rateIdTextView.text = id
                            rateSymbolTextView.text = symbol
                            rateRateUsdTextView.text = rateUsd
                        }
                    }

                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    rate.open(rateListener)
                }
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : RatesViewHolder(binding.root) {
            override fun bind(rate: RateUi) {
                rate.map(object : RateUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(id: String, symbol: String, rateUsd: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface RateListener {
        fun showRate(id: String, symbol: String, rateUsd: String)
    }
}