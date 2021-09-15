package com.selim.expensetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R

class SetupAccountFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_setup_account, container, false)

        view.findViewById<Button>(R.id.letsGoButton).setOnClickListener {
            findNavController().navigate(R.id.action_setupAccountFragment_to_addNewAccountFragment)
        }

        return view

    }

}