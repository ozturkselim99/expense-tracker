package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.MainActivity
import com.selim.expensetracker.databinding.FragmentSignUpBinding
import com.selim.expensetracker.databinding.FragmentVerificationBinding
import com.selim.expensetracker.utils.FirebaseUtils
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth

class VerificationFragment : Fragment() {

    private var binding: FragmentVerificationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerificationBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this


        binding!!.backToSignUpFromVerification.setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment_to_signUpFragment)
        }
        binding!!.verifyButton.setOnClickListener {
            // isEmailVerified()
            findNavController().navigate(R.id.action_verificationFragment_to_setupAccountFragment)
        }

        return binding!!.root

    }

    private fun isEmailVerified() {
        Handler(Looper.getMainLooper()).postDelayed({
            firebaseAuth.currentUser?.let {
                it.reload()
                if (it.isEmailVerified){
                    findNavController().navigate(R.id.action_verificationFragment_to_setupAccountFragment)
                }
                else{
                    Toast.makeText(
                        requireContext(), "Email not verify",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }, 2000)


    }

}