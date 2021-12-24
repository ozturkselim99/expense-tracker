package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.MainActivity
import com.selim.expensetracker.databinding.FragmentLoginBinding
import com.selim.expensetracker.databinding.FragmentSignUpBinding
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this

        if (firebaseAuth.currentUser!=null && firebaseAuth.currentUser!!.isEmailVerified){
            startActivity(Intent(requireContext(),MainActivity::class.java))
            activity?.onBackPressed();
        }

        binding!!.backToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding!!.signUpGuide.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding!!.signInButton.setOnClickListener {
            if (binding!!.signInTextPersonMail.text.toString()
                    .isNotEmpty() && binding!!.signInTextPersonPassword.text.toString().isNotEmpty()
            ) {
                login(
                    binding!!.signInTextPersonMail.text.toString(),
                    binding!!.signInTextPersonPassword.text.toString()
                )
            }
        }
        return binding!!.root

    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(), "Login succes.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(requireContext(),MainActivity::class.java))
                } else {
                    Toast.makeText(
                        requireContext(), "enter all fields.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}