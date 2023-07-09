package com.veselovvv.coinsapp.presentation.assets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.AssetLayoutBinding
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding

class AssetsAdapter(
    private val retry: Retry,
    private val assetListener: AssetListener
) : RecyclerView.Adapter<AssetsAdapter.AssetsViewHolder>() {
    private val assets = ArrayList<AssetUi>()

    fun update(newList: List<AssetUi>) {
        assets.clear()
        assets.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (assets[position]) {
        is AssetUi.NoResults -> -1
        is AssetUi.Base -> 0
        is AssetUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> AssetsViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> AssetsViewHolder.Base(
            AssetLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            assetListener
        )
        1 -> AssetsViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> AssetsViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetsViewHolder, position: Int) =
        holder.bind(assets[position])

    override fun getItemCount() = assets.size

    abstract class AssetsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(asset: AssetUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : AssetsViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : AssetsViewHolder(binding.root)

        class Base(
            private val binding: AssetLayoutBinding,
            private val assetListener: AssetListener
        ) : AssetsViewHolder(binding.root) {
            override fun bind(asset: AssetUi) {
                asset.map(object : AssetUi.BaseMapper {
                    override fun map(rank: String, symbol: String, name: String) {
                        with(binding) {
                            assetRankTextView.text = rank
                            assetSymbolTextView.text = symbol
                            assetNameTextView.text = name
                        }
                    }

                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    asset.open(assetListener)
                }
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : AssetsViewHolder(binding.root) {
            override fun bind(asset: AssetUi) {
                asset.map(object : AssetUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(rank: String, symbol: String, name: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface AssetListener {
        fun showAsset(rank: String, symbol: String, name: String)
    }
}