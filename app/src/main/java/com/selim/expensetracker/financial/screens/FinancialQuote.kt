package com.selim.expensetracker.financial.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R

class FinancialQuote : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_financial_quote, container, false)

        return view

    }

    override fun onResume() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.violett)
        super.onResume()
    }


}