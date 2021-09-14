package com.selim.expensetracker.financial.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R

class FinancialQuote : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_financial_quote, container, false)
    }

    override fun onResume() {
        requireActivity().window.statusBarColor= ContextCompat.getColor(requireContext(), R.color.violett)
        super.onResume()
    }


}