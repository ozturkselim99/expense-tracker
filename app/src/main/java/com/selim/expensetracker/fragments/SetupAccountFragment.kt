package com.selim.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.FragmentSetupAccountBinding

class SetupAccountFragment : Fragment() {

    private var _binding: FragmentSetupAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSetupAccountBinding.inflate(inflater, container, false)

        initViews()
        return binding.root
    }

    private fun initViews(){
        binding.letsGoButton.setOnClickListener {
            findNavController().navigate(R.id.action_setupAccountFragment_to_addNewAccountFragment)
        }
    }

}