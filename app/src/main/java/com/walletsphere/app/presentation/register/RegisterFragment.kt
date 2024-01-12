package com.walletsphere.app.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.walletsphere.app.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(requireContext().applicationContext))[RegisterViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            with(binding) {
                viewModel.registerUser(editTextUsername.text.toString(), editTextPassword.text.toString(), editTextEmail.text.toString())
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.registerButton.isEnabled = !it
            // TODO add some loading logo
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Successfully registered!!!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToPortfolioFragment())

            } else {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}