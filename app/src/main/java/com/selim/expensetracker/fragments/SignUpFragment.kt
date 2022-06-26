package com.selim.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.FragmentSignUpBinding
import com.selim.expensetracker.utils.setVisible
import com.selim.expensetracker.utils.showToastShort
import com.selim.expensetracker.viewmodel.SignUpViewModel


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.signUpButton.setOnClickListener {
            if (binding.signUpTextPersonMail.text.toString()
                    .isNotEmpty() && binding.signUpTextPersonName.text.toString()
                    .isNotEmpty() && binding.signUpTextPassword.text.toString().isNotEmpty()
            ) {
                viewModel.signUp(
                    binding.signUpTextPersonMail.text.toString(),
                    binding.signUpTextPassword.text.toString(),
                    binding.signUpTextPersonName.text.toString()
                )
                observeLiveData()
            } else {
                showToastShort("Please enter all fields")
            }
        }
    }

    private fun observeLiveData() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.signUpError.setVisible(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                binding.signUpLoading.setVisible(it)
                if (!it) {
                    findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
                }
            }
        }
    }
}