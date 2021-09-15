package com.selim.expensetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.models.User


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        var user = User("s", "selim@gmail.com", "12345")

        view.findViewById<Button>(R.id.signUpButton).setOnClickListener {
            var nameTxt = view.findViewById<EditText>(R.id.signUpTextPersonName).text.toString()
            if (nameTxt == user.name) {
                Toast.makeText(requireContext(), "Kayıt Başarılı", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
            }
        }

        return view

    }

}