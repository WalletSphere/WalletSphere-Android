package com.walletsphere.app.presentation.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walletsphere.app.databinding.ItemTokenBinding

class TokenAdapter:
    RecyclerView.Adapter<TokenAdapter.TokenViewHolder>() {

    private val tokensList = ArrayList<TokenUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokenViewHolder =
        TokenViewHolder(ItemTokenBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: TokenViewHolder, position: Int) =
        holder.bind(tokensList[position])

    fun updateTokens(newTokens: List<TokenUI>) {
        tokensList.clear()
        tokensList.addAll(newTokens)
        tokensList.sortByDescending{ it.count }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tokensList.size

    class TokenViewHolder(private val tokenBinding: ItemTokenBinding):
        RecyclerView.ViewHolder(tokenBinding.root) {

        fun bind(model: TokenUI) {
            tokenBinding.tokenName.text = model.symbol
            tokenBinding.tokenAmount.text = model.count
        }

    }
}