package com.selim.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

class VerificationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_verification, container, false)

        view.findViewById<ImageView>(R.id.backToSignUpFromVerification).setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment_to_signUpFragment)
        }

        view.findViewById<Button>(R.id.verifyButton).setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment_to_setupAccountFragment)
        }

        return view
    }


}