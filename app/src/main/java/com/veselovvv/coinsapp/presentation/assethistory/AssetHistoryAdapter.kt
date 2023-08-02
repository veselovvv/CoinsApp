package com.veselovvv.coinsapp.presentation.assethistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.AssetHistoryLayoutBinding
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding

class AssetHistoryAdapter(
    private val retry: Retry
) : RecyclerView.Adapter<AssetHistoryAdapter.AssetHistoryViewHolder>() {
    private val assetHistory = ArrayList<AssetHistoryUi>()

    fun update(newList: List<AssetHistoryUi>) {
        assetHistory.clear()
        assetHistory.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (assetHistory[position]) {
        is AssetHistoryUi.NoResults -> -1
        is AssetHistoryUi.Base -> 0
        is AssetHistoryUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> AssetHistoryViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> AssetHistoryViewHolder.Base(
            AssetHistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        1 -> AssetHistoryViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> AssetHistoryViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetHistoryViewHolder, position: Int) =
        holder.bind(assetHistory[position])

    override fun getItemCount() = assetHistory.size

    abstract class AssetHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(assetHistory: AssetHistoryUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : AssetHistoryViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : AssetHistoryViewHolder(binding.root)

        class Base(private val binding: AssetHistoryLayoutBinding) : AssetHistoryViewHolder(binding.root) {
            override fun bind(assetHistory: AssetHistoryUi) {
                assetHistory.map(object : AssetHistoryUi.BaseMapper {
                    override fun map(priceUsd: String, time: String) {
                        with(binding) {
                            assetHistoryPriceUsdTextView.text = priceUsd
                            assetHistoryTimeTextView.text = time
                        }
                    }

                    override fun map(text: String) = Unit
                })
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : AssetHistoryViewHolder(binding.root) {
            override fun bind(assetHistory: AssetHistoryUi) {
                assetHistory.map(object : AssetHistoryUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(priceUsd: String, time: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }
}