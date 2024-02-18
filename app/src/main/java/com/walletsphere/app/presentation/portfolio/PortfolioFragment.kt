package com.walletsphere.app.presentation.portfolio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.walletsphere.app.databinding.FragmentPortfolioBinding

class PortfolioFragment : Fragment() {

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var viewModel: PortfolioViewModel
    private lateinit var adapter: TokenAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, PortfolioViewModelFactory(requireContext().applicationContext))[PortfolioViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllBalances()

        adapter = TokenAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.tokensList.observe(viewLifecycleOwner) {
            adapter.updateTokens(it)
        }

    }

}