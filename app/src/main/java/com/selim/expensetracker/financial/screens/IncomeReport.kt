package com.selim.expensetracker.financial.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R


class IncomeReport : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.green)

        return inflater.inflate(R.layout.fragment_income_report, container, false)

    }

    override fun onResume() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.green)
        super.onResume()
    }

}