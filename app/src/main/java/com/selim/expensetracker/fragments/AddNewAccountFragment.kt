package com.selim.expensetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R

class AddNewAccountFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_new_account, container, false)

        view.findViewById<Button>(R.id.addNewAccountContinueButton).setOnClickListener {
            findNavController().navigate(R.id.action_addNewAccountFragment_to_signUpSuccessFragment)
        }
        view.findViewById<ImageView>(R.id.backToSetupAccount).setOnClickListener {
            findNavController().navigate(R.id.action_addNewAccountFragment_to_setupAccountFragment)
        }

        return view

    }

}