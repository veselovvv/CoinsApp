package com.veselovvv.coinsapp.presentation.exchanges

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.coinsapp.core.Retry
import com.veselovvv.coinsapp.databinding.ExchangeLayoutBinding
import com.veselovvv.coinsapp.databinding.FailFullscreenBinding
import com.veselovvv.coinsapp.databinding.NoResultsScreenBinding
import com.veselovvv.coinsapp.databinding.ProgressFullscreenBinding

class ExchangesAdapter(
    private val retry: Retry,
    private val exchangeListener: ExchangeListener
) : RecyclerView.Adapter<ExchangesAdapter.ExchangesViewHolder>() {
    private val exchanges = ArrayList<ExchangeUi>()

    fun update(newList: List<ExchangeUi>) {
        exchanges.clear()
        exchanges.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (exchanges[position]) {
        is ExchangeUi.NoResults -> -1
        is ExchangeUi.Base -> 0
        is ExchangeUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        -1 -> ExchangesViewHolder.NoResults(
            NoResultsScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        0 -> ExchangesViewHolder.Base(
            ExchangeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            exchangeListener
        )
        1 -> ExchangesViewHolder.Fail(
            FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
        else -> ExchangesViewHolder.FullscreenProgress(
            ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExchangesViewHolder, position: Int) =
        holder.bind(exchanges[position])

    override fun getItemCount() = exchanges.size

    abstract class ExchangesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(exchange: ExchangeUi) = Unit

        class FullscreenProgress(binding: ProgressFullscreenBinding) : ExchangesViewHolder(binding.root)

        class NoResults(binding: NoResultsScreenBinding) : ExchangesViewHolder(binding.root)

        class Base(
            private val binding: ExchangeLayoutBinding,
            private val exchangeListener: ExchangeListener
        ) : ExchangesViewHolder(binding.root) {
            override fun bind(exchange: ExchangeUi) {
                exchange.map(object : ExchangeUi.BaseMapper {
                    override fun map(id: String, name: String, rank: String) {
                        with(binding) {
                            exchangeIdTextView.text = id
                            exchangeNameTextView.text = name
                            exchangeRankTextView.text = rank
                        }
                    }

                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    exchange.open(exchangeListener)
                }
            }
        }

        class Fail(
            private val binding: FailFullscreenBinding,
            private val retry: Retry
        ) : ExchangesViewHolder(binding.root) {
            override fun bind(exchange: ExchangeUi) {
                exchange.map(object : ExchangeUi.BaseMapper {
                    override fun map(text: String) {
                        binding.failMessageTextView.text = text
                    }

                    override fun map(id: String, name: String, rank: String) = Unit
                })

                binding.failTryAgainButton.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface ExchangeListener {
        fun showExchange(id: String, name: String, rank: String)
    }
}