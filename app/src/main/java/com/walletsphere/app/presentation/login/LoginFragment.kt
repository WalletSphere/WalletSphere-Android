package com.walletsphere.app.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.walletsphere.app.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(requireContext().applicationContext))[LoginViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            with(binding) {
                viewModel.loginUser(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loginButton.isEnabled = !it
            // TODO add some loading logo
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Successfully login!!!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPortfolioFragment())

            } else {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}