package com.selim.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.UserProfileChangeRequest
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.FragmentSignUpBinding
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth
import com.selim.expensetracker.utils.FirebaseUtils.firebaseFirestore


class SignUpFragment : Fragment() {
    private var binding: FragmentSignUpBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this


        binding!!.signUpButton.setOnClickListener {
            if (binding!!.signUpTextPersonMail.text.toString()
                    .isNotEmpty() && binding!!.signUpTextPersonName.text.toString()
                    .isNotEmpty() && binding!!.signUpTextPassword.text.toString().isNotEmpty()
            ) {
                signUp(
                    binding!!.signUpTextPersonMail.text.toString(),
                    binding!!.signUpTextPassword.text.toString(),
                    binding!!.signUpTextPersonName.text.toString()
                )

            } else {
                Toast.makeText(
                    requireContext(), "Please enter all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return binding!!.root
    }

    private fun signUp(email: String, password: String, name: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    if (firebaseAuth.currentUser?.uid != null) {
                        val request = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                        firebaseAuth.currentUser?.updateProfile(request)?.addOnSuccessListener {
                            addUserToFirebase()
                        }?.addOnFailureListener {
                            Toast.makeText(
                                requireContext(),
                                " Display name failed to update",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun sendEmailVerification() {
        firebaseAuth.currentUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(), "Email sent",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
                }
            }
        }
    }

    private fun addUserToFirebase() {
        val user = hashMapOf(
            "userId" to firebaseAuth.currentUser!!.uid,
            "name" to firebaseAuth.currentUser!!.displayName,
            "email" to firebaseAuth.currentUser!!.email,
        )
        firebaseFirestore?.collection("Users")
            ?.document(firebaseAuth.currentUser!!.uid)?.set(
                user
            )
            ?.addOnSuccessListener {
                //sendEmailVerification()
                findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
                Toast.makeText(
                    requireContext(), "Authentication Success.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ?.addOnFailureListener {
                Toast.makeText(
                    requireContext(), "Authentication Failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}