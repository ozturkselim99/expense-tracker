package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.MainActivity
import com.selim.expensetracker.databinding.FragmentLoginBinding
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth
import com.selim.expensetracker.utils.setVisible
import com.selim.expensetracker.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        initViews()
        userAuthControl()
        return binding.root
    }

    private fun userAuthControl() {
        if (firebaseAuth.currentUser != null && firebaseAuth.currentUser!!.isEmailVerified) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity?.onBackPressed()
        }
    }

    private fun initViews() {
        binding.backToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.signUpGuide.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.signInButton.setOnClickListener {
            if (binding.signInTextPersonMail.text.toString()
                    .isNotEmpty() && binding.signInTextPersonPassword.text.toString().isNotEmpty()
            ) {
                viewModel.login(
                    binding.signInTextPersonMail.text.toString(),
                    binding.signInTextPersonPassword.text.toString()
                )
                observeLiveData()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.loginError.setVisible(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                binding.loginLoading.setVisible(it)
                if (!it) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
            }
        }
    }
}