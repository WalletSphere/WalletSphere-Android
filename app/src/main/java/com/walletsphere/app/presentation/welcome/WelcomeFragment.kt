package com.walletsphere.app.presentation.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.walletsphere.app.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, WelcomeViewModelFactory(requireContext().applicationContext))[WelcomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            findNavController()
                .navigate(
                    WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
                )
        }

        binding.loginButton.setOnClickListener {
            findNavController()
                .navigate(
                    WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
                )
        }

        viewModel.checkIfUserAuthorized()

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(
                    WelcomeFragmentDirections.actionWelcomeFragmentToPortfolioFragment()
                )
            }
        }
    }

}