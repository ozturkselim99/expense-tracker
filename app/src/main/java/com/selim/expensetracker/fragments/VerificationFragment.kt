package com.selim.expensetracker.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.FragmentVerificationBinding
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth
import com.selim.expensetracker.utils.showToastShort

class VerificationFragment : Fragment() {

    private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVerificationBinding.inflate(inflater, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.backToSignUpFromVerification.setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment_to_signUpFragment)
        }
        binding.verifyButton.setOnClickListener {
            // isEmailVerified()
            findNavController().navigate(R.id.action_verificationFragment_to_setupAccountFragment)
        }
    }

    //TODO:bakılacak
    private fun isEmailVerified() {
        Handler(Looper.getMainLooper()).postDelayed({
            firebaseAuth.currentUser?.let {
                it.reload()
                if (it.isEmailVerified) {
                    findNavController().navigate(R.id.action_verificationFragment_to_setupAccountFragment)
                } else {
                    showToastShort("Email not verify")
                }
            }
        }, 2000)
    }
}